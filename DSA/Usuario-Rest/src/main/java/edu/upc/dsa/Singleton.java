package edu.upc.dsa;

import java.util.*;
import java.util.logging.Logger;

public class Singleton implements Manager
{
    private static final Logger logger = Logger.getLogger("MyLogger");
    private static Singleton instance = null;
    private Usuario usuario;
    private HashMap<String, Usuario> list;

    Objeto pocion = new Objeto("Pocion", 50);
    Objeto eter = new Objeto("Eter", 10);
    Vector<Objeto> obj = new Vector<Objeto>();

    //Instancia única
    private Singleton()
    {
        list = new HashMap<String, Usuario>();
        Usuario test = new Usuario();
        test.setNombre("Ezio");
        test.setEdad(49);
        obj.add(pocion);
        obj.add(eter);
        test.setObjeto(obj);
        list.put("Ezio", test);
    }

    //Devuelve la instancia, si no existe la crea
    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    //Añade un usuario al HashMap
    public boolean añadirUsuario(String nombre, int edad)
    {
        Usuario test = list.get(nombre);
        if (test == null)
        {
            usuario = new Usuario(nombre, edad);
            list.put(nombre, usuario);
            logger.info("Nuevo Usuario ("+list.get(nombre).getNombre()+", "+list.get(nombre).getEdad()+")");
            return true;
        }
        else
        {
            logger.info("ERROR usuario existente en HashMap");
            return  false;
        }
    }

    //Elimina un usuario del HashMap
    public boolean eliminarUsuario(String nombre)
    {
        Usuario eliminado = list.remove(list.get(nombre).getNombre());
        if (eliminado == null)
        {
            logger.info("ERROR usuario no eliminado");
            return false;
        }
        else
        {
            logger.info("Usuario "+nombre+" Eliminado");
            return true;
        }
    }

    //Devuelve un usuario del HasMap
    public Usuario consultarUsuario(String nombre)
    {
        if (list.get(nombre) == null)
        {
            logger.info("ERROR: Usuario "+nombre+" no existe");
            return null;
        }
        logger.info("Usuario consultado ("+list.get(nombre).getNombre()+", "+list.get(nombre).getEdad()+")");
        return list.get(nombre);
    }

    //Devuelve una lista con los usuarios ordenados alfabeticamente
    public Vector<Usuario> ordenarAlfabeticamente()
    {
        Vector<Usuario> orden = new Vector<Usuario>();
        Map mapOrdenado = new TreeMap(list);
        Set ref = mapOrdenado.keySet();
        Iterator it = ref.iterator();
        while (it.hasNext())
        {
            orden.add(list.get((String) it.next()));
        }
        logger.info("Lista de usuarios ordenados ("+list.size()+")");
        return orden;
    }

    //Añade un Objeto a un usuario
    public void añadirObjeto(String nombre, Objeto objeto)
    {
        list.get(nombre).getObjeto().add(objeto);
        logger.info("Objeto "+objeto.getNombre()+" se ha añadido a "+list.get(nombre).getNombre());
    }

    //Devuelve un vector de objetos
    public Vector<Objeto> consultarObjetos(String nombre)
    {
        logger.info("Usuario "+list.get(nombre).getNombre()+" tiene "+list.get(nombre).getObjeto().size()+" objetos");
        return list.get(nombre).getObjeto();
    }
}