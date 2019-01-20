package dsa.upc.edu.minim2.object;

import com.google.gson.annotations.SerializedName;

public class Producto
{
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("precio")
    private int precio;
    @SerializedName("cantidad")
    private int cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Constructor
    public Producto() { }
}
