package eetac.dsa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSingleton {


    @Test
    public void testEspañol()
    {
        assertEquals("Abrir",Singleton.getInstance().getText("es","etiqueta_boton_abrir"));
    }

    @Test
    public void testIngles()
    {
        assertEquals("Open",Singleton.getInstance().getText("en","etiqueta_boton_abrir"));
    }
}
