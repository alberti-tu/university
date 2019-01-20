package edu.upc.dsa;

class QueueImpl<T> implements Queue<T>{

    T[] arrayObjectes;
    int intSize;
    int max;

    public  QueueImpl(int max)
    {
        arrayObjectes = (T[])new Object[max];
        intSize = 0;
        this.max = max;
    }

    public T pop() {

        if(intSize ==0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }

        T o = arrayObjectes[0];

        for(int i=0;i<intSize;i++)
        {
            arrayObjectes[i] = arrayObjectes[i+1];
        }
        intSize--;
        return o;
    }

    public void push(T pushObj) {
        if(intSize>=max)
        {
            throw new ArrayIndexOutOfBoundsException();
        }

        arrayObjectes[intSize] = pushObj;
        intSize ++;
    }

    public int size() {
        return intSize;
    }
}