package edu.upc.dsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class MainTest
{
    private static final Logger log = LogManager.getLogger(MainTest.class.getName());
    private ProductManager bar;
    private Vector<Producto> list;
    private Pedido pedido;

    @Before
    public void setUp()
    {
        log.info("--> setUp");
        bar = ProductManager.getInstance();
        list = new Vector<Producto>();

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

        pedido = new Pedido();
        pedido.setUsuario("Albert");
        pedido.setPedido(list);
    }

    @After
    public void tearDown()
    {
        log.info("--> tearDown");
        bar.eliminarProductos();        //Elimina todos los elementos de la lista
    }

    @Test
    public void realizarPedido()
    {
        log.info("--> realizarPedido");
        assertEquals(1, bar.pedidoUsuario(pedido));     //1 pedido en la cola (usuario = Albert, 3 productos)
    }

    @Test
    public void realizarServicio()
    {
        log.info("--> realizarServicio");
        bar.pedidoUsuario(pedido);
        assertEquals(pedido.getUsuario(), bar.servirPedido().getUsuario());
    }

}
