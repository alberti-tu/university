package eetac.dsa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertEquals;

public class AppTest
{
    Factory f = null;
    @org.junit.Before
    public void setUp()
    {
        this.f = Factory.getInstncia();
    }

    @org.junit.After
    public void tearDown()
    {
        this.f.reset();
    }

    @org.junit.Test
    public void Test_C1()
    {
        assertEquals(1, f.getCommand("C1").execute());
        assertEquals(1, f.getCommand("C1").execute());
    }

    @org.junit.Test
    public void Test_C2()
    {
        assertEquals(2, f.getCommand("C2").execute());
    }

    @org.junit.Test
    public void Test_C3()
    {
        assertEquals(3, f.getCommand("C3").execute());
    }

    @org.junit.Test
    public void Test_MIX()
    {
        assertEquals(1, f.getCommand("C1").execute());
        assertEquals(2, f.getCommand("C2").execute());
        assertEquals(3, f.getCommand("C3").execute());
    }
}
