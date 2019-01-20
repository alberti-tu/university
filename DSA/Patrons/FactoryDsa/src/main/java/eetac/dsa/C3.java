package eetac.dsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C3 implements Command
{
    private static final Logger logger = LogManager.getLogger(C3.class.getName());

    public int execute()
    {
        logger.debug("Estoy en " + C3.class.getName());
        return 3;
    }
}
