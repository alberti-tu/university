package eetac.ads.omv;
import org.apache.logging.log4j.*;

public class QueueImpl<T> implements QueueCt<T>{

    T[] arrayObjectes;
    int intSize;
    int max;
    private static final Logger logger = LogManager.getLogger(QueueImpl.class.getName());

    public  QueueImpl(int max)
    {

        arrayObjectes = (T[])new Object[max];
        intSize = 0;
        this.max = max;
        logger.debug("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));

    }

    public T pop() {

        logger.debug("es fa el pop");
        if(intSize ==0)
        {
            logger.error("error al pop");
            throw new ArrayIndexOutOfBoundsException();


        }

        T o = arrayObjectes[0];

        for(int i=0;i<intSize;i++)
        {
            arrayObjectes[i] = arrayObjectes[i+1];
        }
        intSize--;
        logger.debug("el pop retorna "+o);
        return o;
    }

    public void push(T pushObj) {
        logger.debug("es fa el push "+pushObj);
        if(intSize>=max)
        {
            logger.error("error al push");
            throw new ArrayIndexOutOfBoundsException();
        }

        arrayObjectes[intSize] = pushObj;
        intSize ++;
    }

    public int size() {
        logger.debug("es demana el tamany");
        return intSize;
    }
}
