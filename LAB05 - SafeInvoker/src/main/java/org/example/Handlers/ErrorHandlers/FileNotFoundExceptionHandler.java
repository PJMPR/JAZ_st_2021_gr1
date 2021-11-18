package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.ErrorBase;
import org.example.Suppliers.MethodSupplier;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionHandler extends ErrorBase {
    public FileNotFoundExceptionHandler() {
        super(FileNotFoundExceptionHandler.class);
    }

    @Override
    public void handle(MethodSupplier method, Exception e) {
        if(retry(method, 5, 1000)){
            defaultResponse(e);
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }

    @Override
    public String getMessage() {
        return "File was not found.";
    }
}
