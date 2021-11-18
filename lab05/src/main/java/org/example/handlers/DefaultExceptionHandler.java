package org.example.handlers;

import org.example.Supplier;

public class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "Something went wrong";
    }

    @Override
    public void handle(Exception error, Supplier method) {
        System.out.println(getMessage());
        logger.log(getMessage());
    }

    @Override
    public boolean canHandle(Exception error) {
        return true;
    }
}

