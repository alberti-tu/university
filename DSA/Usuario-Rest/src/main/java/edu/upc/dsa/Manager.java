package edu.upc.dsa;

import java.util.Vector;

public interface Manager
{
    boolean añadirUsuario(String nombre, int edad);

    boolean eliminarUsuario(String nombre);

    Usuario consultarUsuario(String nombre);

    Vector<Usuario> ordenarAlfabeticamente();

    void añadirObjeto(String nombre, Objeto objeto);

    Vector<Objeto> consultarObjetos(String nombre);
}
