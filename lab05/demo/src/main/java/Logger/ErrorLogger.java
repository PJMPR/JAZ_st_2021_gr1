package Logger;

import org.apache.log4j.Logger;

public class ErrorLogger {
    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);

    public void logError(String message){
        log.error(message);
    }

    public void logError(String message, Exception error){
        log.error(message,error);
    }
}


