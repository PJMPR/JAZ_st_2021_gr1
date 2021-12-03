package org.example.Handlers;

import org.example.Supplier.Supplier;

public class DefaultHandler implements Handler{
    @Override
    public String getMessage() {
        return "Something went wrong...";
    }

    @Override
    public boolean handle(Exception e, Supplier method) {
        System.out.println(getMessage());
        logger.log(getMessage(), e);
        return true;
    }

    @Override
    public boolean canHandle(Exception e) {
        return true;
    }
}
