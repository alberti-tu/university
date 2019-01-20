package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Vector;

@Path("/json")
public class JSONService
{
    //Devuelve la lista de productos ordenada por precio
    @GET
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getListPrecio()
    {
        return ProductManager.getInstance().listaProductosPrecio();
    }

    //Devuelve la lista de productos ordenada por ventas
    @GET
    @Path("/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getListVentas()
    {
        return ProductManager.getInstance().listaProductosVentas();
    }

    //Lista de pedidos del Usuario
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vector<Producto> getListPedido(@PathParam("username") String userName)
    {
        return ProductManager.getInstance().listaPedidosUsuario(userName);
    }

    //Enviamos un pedido a la cola de espera
    @POST
    @Path("/pedido")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enviarPedido(Pedido pedido)
    {
/*
        URL: localhost:8080/myapp/json/pedido
        BODY:
                {
                    "usuario":"Albert",
                    "pedido":
	                    [
                            { "cantidad": 1, "nombre": "Zumo", "precio": 6 },
                            { "cantidad": 5, "nombre": "Agua", "precio": 2 }
	                    ]
                }
*/
        ProductManager.getInstance().pedidoUsuario(pedido);
        return Response.status(202).entity("Pedido en espera").build();
    }

    //Se atiene los pedidos en espera
    @GET
    @Path("/servir")
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido getPedido()
    {
        return ProductManager.getInstance().servirPedido();
    }
}