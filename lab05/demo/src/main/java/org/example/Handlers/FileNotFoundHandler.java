package org.example.Handlers;

import org.example.Supplier.Supplier;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

public class FileNotFoundHandler implements Handler{
    @Override
    public String getMessage() {
        return "404 File not found";
    }
    @Override
    public boolean handle(Exception e, Supplier method) {
        if(canHandle(e))
        {
            System.out.println(getMessage());
            logger.log(getMessage());
            return true;
        }
        return false;
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof FileNotFoundException;
    }
}
