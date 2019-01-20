package edu.upc.dsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Vector;

public class ProductManager implements Fachada
{
    private static final Logger log = LogManager.getLogger(ProductManager.class.getName());
    private static ProductManager instance = null;
    private Vector<Producto> list;
    private HashMap<String, Pedido> clientes;
    private Queue<Pedido> queue;

    //Instancia única
    private ProductManager()
    {
        list = new Vector<Producto>();
        clientes = new HashMap<String, Pedido>();
        queue = new QueueImpl<>(10);

        Ejemplo();  //Rellena la lista con 3 productos
    }

    //Devuelve la instancia, si no existe la crea
    public static ProductManager getInstance()
    {
        if (instance == null)
        {
            log.info("Instancias creadas");
            instance = new ProductManager();
        }
        return instance;
    }

    //Añadir producto
    public void añadirProducto(String nombre, int num, int price)
    {
        Producto prod = new Producto();
        prod.setNombre(nombre);
        prod.setCantidad(num);
        prod.setPrecio(price);
        log.info("Producto añadido");
        list.add(prod);
    }

    //Elimina todos los productos
    public void eliminarProductos()
    {
        log.info("Productos eliminados");
        list.clear();
    }

    //Lista de Productos ordenados por precio
    @Override
    public Vector<Producto> listaProductosPrecio()
    {
        Vector<Producto> ordenPrecio = new Vector<Producto>();
        int precioMin = list.elementAt(0).getPrecio();

        //Busca el precio más bajo de la lista
        for (int i = 0; i < list.size(); i++)
        {
            if (precioMin > list.elementAt(i).getPrecio())
            {
                precioMin = list.elementAt(i).getPrecio();
            }
        }

        //Ordena la lista de forma ascendente
        int i = 0, elementos = 0;
        while (elementos < list.size())
        {
            if (precioMin == list.elementAt(i).getPrecio())
            {
                ordenPrecio.add(list.elementAt(i));
                elementos++;
            }
            i++;
            if (i == list.size())
            {
                i = 0;
                precioMin++;
            }
        }

        log.info("Lista de Productos ordenados por precio (" + ordenPrecio.size() + " elementos)");
        return ordenPrecio;
    }

    @Override
    public Vector<Producto> listaProductosVentas()
    {
        Vector<Producto> ordenVentas = new Vector<Producto>();
        int ventasMax = list.elementAt(0).getCantidad();

        //Busca el precio más bajo de la lista
        for (int i = 0; i < list.size(); i++)
        {
            if (ventasMax < list.elementAt(i).getCantidad())
            {
                ventasMax = list.elementAt(i).getCantidad();
            }
        }

        //Ordena la lista de forma descendente
        int i = 0, elementos = 0;
        while (elementos < list.size())
        {
            if (list.elementAt(i).getCantidad() == ventasMax)
            {
                ordenVentas.add(list.elementAt(i));
                elementos++;
            }
            i++;
            if (i == list.size())
            {
                i = 0;
                ventasMax--;
            }
        }

        log.info("Lista de Productos ordenados por ventas (" + ordenVentas.size() + " elementos)");
        return ordenVentas;
    }

    //Lista de pedidos de un usuario
    @Override
    public Vector<Producto> listaPedidosUsuario(String usuario)
    {
        if (clientes.get(usuario) == null)
        {
            log.error("Usuario " + usuario + " no encontrado");
            return null;
        }
        else
        {
            log.info("Lista de pedidos del usuario " + usuario);
            return clientes.get(usuario).getPedido();
        }
    }

    //Añade un pedido a la cola
    @Override
    public int pedidoUsuario(Pedido pedido)
    {
        if (pedido.getPedido() == null)
        {
            log.error("No se ha hecho un pedido");
            return -1;
        }
        queue.push(pedido);
        log.info("El pedido del usuario " + pedido.getUsuario() + " está a la espera de servicio");

        //Actualizar ventas del producto
        for(int i = 0; i < pedido.getPedido().size(); i++)
        {
            for(int j = 0; j < list.size(); j++)
            {
                if(pedido.getPedido().elementAt(i).getNombre().equals(list.elementAt(j).getNombre()))
                {
                    list.elementAt(j).setCantidad(list.elementAt(j).getCantidad() + pedido.getPedido().elementAt(i).getCantidad());
                    log.info("El producto "+list.elementAt(j).getNombre()+" se ha vendido "+list.elementAt(j).getCantidad()+" veces");
                }
            }
        }

        clientes.put(pedido.getUsuario(), pedido);
        log.info("Actualizado historial del usuario " + pedido.getUsuario());

        return queue.size();
    }

    //Servir los pedidos
    @Override
    public Pedido servirPedido()
    {
        if (queue.size() == 0)
        {
            log.error("No hay peticiones en espera");
            return  null;
        }
        log.info("Peticion servida (peticiones sin servir "+(queue.size() -1)+")");
        return queue.pop();
    }

    public Vector<Producto> Ejemplo()
    {
        Producto cerveza = new Producto();
        cerveza.setNombre("Cerveza");
        cerveza.setCantidad(8);
        cerveza.setPrecio(5);
        list.add(cerveza);

        Producto agua = new Producto();
        agua.setNombre("Agua");
        agua.setCantidad(4);
        agua.setPrecio(2);
        list.add(agua);

        Producto zumo = new Producto();
        zumo.setNombre("Zumo");
        zumo.setCantidad(9);
        zumo.setPrecio(6);
        list.add(zumo);

        return list;
    }
}