package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/json")
public class JSONService {

    protected List<Track> tracks;

    public JSONService() {
        tracks = new ArrayList<>();

        Track t1 = new Track();
        t1.setTitle("Enter Sandman");
        t1.setSinger("Metallica");
        tracks.add(t1);

        Track t2 = new Track();
        t2.setTitle("La Barbacoa");
        t2.setSinger("Georgie Dann");
        tracks.add(t2);
    }

    @GET
    @Path("/got/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(@PathParam("id") int id) {
        return tracks.get(id);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getTrackInJSON() {
        Usuario user = new Usuario();
        user.setNombre("Albert");
        return user;
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Track track) {
        tracks.add(track);
        // Atencion: siempre añade en la misma posicion por el scope de tracks
        return Response.status(201).entity("Track added in position " + tracks.size()).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();
    }
/*
    //Devuelve JSON de la web
    @GET
    @Path("/buscar/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser(@PathParam("nombre") String nombre) {
        return Singleton.getInstance().getListausuarios().get(nombre);
    }

    //Envia un JSON a la web
    @POST
    @Path("/enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserInJSON(Usuario user) {
        String result = "User saved : " + user;
        return Response.status(201).entity(result).build();
    }
*/
}
