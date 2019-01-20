package edu.upc.dsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest
{
    private Singleton list;
    private Objeto pocion;
    private Objeto eter;

    @Before
    public void setUp()
    {
        list = Singleton.getInstance();
        list.añadirUsuario("Albert", 20);
        list.añadirUsuario("Maria", 22);
        list.añadirUsuario("Juan", 45);

        pocion = new Objeto("Pocion", 50);
        eter = new Objeto("Eter", 10);

        list.añadirObjeto("Albert", pocion);
        list.añadirObjeto("Albert", eter);
        list.añadirObjeto("Maria", eter);
    }

    @After
    public void tearDown()
    {
        list.consultarObjetos("Albert").clear();
        list.consultarObjetos("Maria").clear();
        list.consultarObjetos("Juan").clear();

        list.eliminarUsuario("Albert");
        list.eliminarUsuario("Maria");
        list.eliminarUsuario("Juan");
    }

    @org.junit.Test
    public void añadirUsuario()
    {
        //Añadimos 3 más el usuario que se inicializa en el Singleton
        assertEquals(4, list.ordenarAlfabeticamente().size());
    }

    @org.junit.Test
    public void añadirObjeto()
    {
        assertEquals(2, list.consultarObjetos("Albert").size());
        assertEquals(1, list.consultarObjetos("Maria").size());
        assertEquals(0, list.consultarObjetos("Juan").size());
    }
}
