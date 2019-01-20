package dsa.upc.edu.minim2.rest;


import java.util.List;
import java.util.Vector;

import dsa.upc.edu.minim2.object.KeyUser;
import dsa.upc.edu.minim2.object.Producto;
import dsa.upc.edu.minim2.object.UsuarioJSON;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIservice
{
    @POST("auth/login")
    Call<KeyUser> login(@Body UsuarioJSON usuario);

    @GET("user/precio")
    Call<List<Producto>> getPrecio();

    @GET("user/{nombre}/pedidos")
    Call<Vector<Producto>> getPedido(@Path("nombre") String nombre);

    @POST("user/pedido")
    Call<Response> pedido(@Body List<Producto> list);
}