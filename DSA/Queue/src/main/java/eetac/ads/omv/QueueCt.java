package eetac.ads.omv;

public interface QueueCt<T> {
    /**
     * mètode
     * @param push aquest paràmetre es el objecte guardat a la cua
     */
     void push(T push);
     T pop();
     int size();
}
