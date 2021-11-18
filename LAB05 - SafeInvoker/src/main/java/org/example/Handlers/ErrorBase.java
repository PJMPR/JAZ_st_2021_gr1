package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Suppliers.MethodSupplier;

import java.util.concurrent.TimeUnit;

public abstract class ErrorBase implements ErrorHandler{
    private final Logger logger;

    public ErrorBase(Class<?> clazz){
            logger = Logger.getLogger(clazz);
    }

    public void defaultResponse(Exception e){
        System.out.println(getMessage());
        logger.error(getMessage(), e);
    }

    public boolean retry(MethodSupplier method, int times, int millis) {
        for (int i = times; i > 0; i--) {
            try {
                method.execute();
                return false;
            } catch (Exception e) {
                wait(TimeUnit.MILLISECONDS, millis);
            }
        }
        return true;
    }

    public void wait(TimeUnit timeUnit, int number) {
        try {
            timeUnit.sleep(number);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
