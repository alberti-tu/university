package edu.upc.dsa;

import java.util.List;
import java.util.Vector;

public interface Fachada
{
    Vector<Producto> listaProductosPrecio();    //Devuelve una lista ordenada por precio ascendente

    Vector<Producto> listaProductosVentas();    //Devuelve una lista ordenada por ventas descendente

    Vector<Producto> listaPedidosUsuario(String usuario);   //Devuelve una lista con los pedidios de un usuario

    int pedidoUsuario(Pedido pedido);   //Un usuario deja su pedido en una cola de espera

    Pedido servirPedido();     //Se atienden los pedidos en espera
}
