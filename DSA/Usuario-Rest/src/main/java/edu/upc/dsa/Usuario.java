package edu.upc.dsa;

import java.util.*;
import java.util.logging.Logger;

public class Usuario {

    private String nombre;
    private int edad;
    private Vector<Objeto> objeto;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public Vector<Objeto> getObjeto() { return objeto; }
    public void setObjeto(Vector<Objeto> objeto) { this.objeto = objeto; }

    //Constructor vacio
    public Usuario()
    {

    }

    public Usuario(String nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
        this.objeto = new Vector<Objeto>();
    }
}
