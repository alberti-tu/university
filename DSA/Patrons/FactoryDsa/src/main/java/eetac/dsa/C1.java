package eetac.dsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C1 implements Command
{
    private static final Logger logger = LogManager.getLogger(C1.class.getName());

    public int execute()
    {
        logger.debug("Estoy en " + C1.class.getName());
        return 1;
    }
}
