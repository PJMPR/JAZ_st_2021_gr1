package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.ErrorBase;
import org.example.Suppliers.MethodSupplier;

import java.io.IOException;

public class IOExceptionHandler extends ErrorBase {
    public IOExceptionHandler() {
        super(IOExceptionHandler.class);
    }

    @Override
    public void handle(MethodSupplier method, Exception e) {
        if(retry(method, 10, 1000)){
            defaultResponse(e);
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof IOException;
    }

    @Override
    public String getMessage() {
        return "Unable to get to the resource";
    }
}
