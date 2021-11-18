package org.example.handlers;

import org.example.Supplier;
import java.io.FileNotFoundException;

public class FileNotFoundExceptionHandler implements ExceptionHandler {

    @Override
    public String getMessage() {
        return "File was not found. Check your path file";
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
        return error instanceof FileNotFoundException;
    }
}

