package eetac.dsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C2 implements Command
{
    private static final Logger logger = LogManager.getLogger(C2.class.getName());

    public int execute()
    {
        logger.debug("Estoy en " + C2.class.getName());
        return 2;
    }
}
