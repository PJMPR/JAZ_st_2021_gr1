package org.example.Logger;

import org.apache.log4j.Logger;

public class ExceptionLogger {
    Logger logger= Logger.getLogger(Logger.class);

    public void log(String msg){
        logger.error(msg);
    }

}