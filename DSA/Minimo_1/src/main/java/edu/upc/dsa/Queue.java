package edu.upc.dsa;

public interface Queue<T>
{
    /**
     * mètode
     * @param push aquest paràmetre es el objecte guardat a la cua
     */
    void push(T push);
    T pop();
    int size();
}