package edu.upc.dsa;

public class Objeto
{
    private String nombre;
    private int salud;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getSalud() { return salud; }
    public void setSalud(int salud) { this.salud = salud; }

    public Objeto(String nombre, int salud)
    {
        this.nombre = nombre;
        this.salud = salud;
    }
}
