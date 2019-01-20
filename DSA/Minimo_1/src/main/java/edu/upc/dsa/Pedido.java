package edu.upc.dsa;

import java.util.Vector;

public class Pedido
{
    private String usuario;
    private Vector<Producto> pedido;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Vector<Producto> getPedido() {
        return pedido;
    }

    public void setPedido(Vector<Producto> pedido) {
        this.pedido = pedido;
    }

    public Pedido()
    {
        this.pedido = new Vector<Producto>();
    }
}