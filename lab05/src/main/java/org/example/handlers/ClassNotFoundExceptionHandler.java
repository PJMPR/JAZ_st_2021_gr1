package org.example.handlers;

import org.example.Supplier;

public class ClassNotFoundExceptionHandler implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "Class could not be found";
    }

    @Override
    public void handle(Exception error, Supplier method) {
        if(canHandle(error)){
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return error instanceof ClassNotFoundException;
    }
}
