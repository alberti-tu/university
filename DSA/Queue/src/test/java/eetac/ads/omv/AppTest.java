package eetac.ads.omv;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AppTest
{
    @Test
    public void Cua_Buida()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        assertEquals(0, queue.size());
    }

    @Test
    public void Cua_Plena()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        for(int i = 0; i < 10; i++)
        {
            queue.push(i);
        }
        assertEquals(10, queue.size());
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void Cua_Plena_Exception()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        for(int i = 0; i < 11; i++)
        {
            queue.push(i);
        }
        assertEquals(11, queue.size());
    }

    @Test
    public void Cua_Afegir()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        queue.push(50);
        assertEquals(1, queue.size());
    }

    @Test
    public void Cua_Treure()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        queue.push(50);
        queue.pop();
        assertEquals(0, queue.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void Cua_Treure_Exception()
    {
        QueueCt<Integer> queue = new QueueImpl<Integer>(10);
        queue.pop();
        assertEquals(0, queue.size());
    }
}