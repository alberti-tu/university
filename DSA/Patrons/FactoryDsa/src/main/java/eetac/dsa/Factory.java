package eetac.dsa;

import java.util.HashMap;
import org.apache.logging.log4j.*;

public class Factory {
    HashMap<String,Command> comandas;

    static Factory instancia;

    private static final Logger logger = LogManager.getLogger(Factory.class.getName());

    private Factory ()
    {
        comandas = new HashMap<String, Command>();
    }

    static public Factory getInstncia() {
        if(instancia==null)
        {
            instancia = new Factory();
        }

        logger.debug("obtinc la instancia");
        return instancia;
    }

    public Command getCommand(String command)
    {
        logger.debug("obtinc un command");
        Command rel = comandas.get(command);

        if(rel == null)
        {
            logger.debug("Utilitzo ClassLoader");
            try {
                Class d = Class.forName("eetac.dsa."+command);
                Command commanda = (Command)d.newInstance();
                comandas.put(command,commanda);
                return commanda;
            }
            catch (ClassNotFoundException e)
            {
                logger.error("classe no trobada");
                return null;
            }catch(InstantiationException e)
            {
                logger.error("no es pot instancia la classe");
                return null;
            }catch (IllegalAccessException e)
            {
                logger.error("no es pot accedir a la clasee");
                return null;
            }
        }
        else logger.debug("Utilitzo cache");

        return rel;
    }

    public static void reset() {
        instancia=null;
    }
}
