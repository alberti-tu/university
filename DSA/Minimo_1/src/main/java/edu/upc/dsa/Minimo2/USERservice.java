package edu.upc.dsa.Minimo2;

import edu.upc.dsa.Pedido;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.Producto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Vector;

@Path("/user")
public class USERservice
{
    //Devuelve la lista de productos ordenada por precio
    @GET
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getListPrecio()
    {
        return ProductManager.getInstance().listaProductosPrecio();
    }


    //Lista de pedidos del Usuario
    @GET
    @Path("/{username}/pedidos")
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
        ProductManager.getInstance().pedidoUsuario(pedido);
        return Response.status(202).entity("Pedido en espera").build();
    }
}
