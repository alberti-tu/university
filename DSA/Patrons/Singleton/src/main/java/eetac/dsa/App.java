package eetac.dsa;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Singleton idioma = Singleton.getInstance();

        idioma.getText("es", "etiqueta_boton_abrir");
        idioma.getText("en", "etiqueta_boton_cerrar");
        idioma.getText("ca", "etiqueta_error");
    }
}