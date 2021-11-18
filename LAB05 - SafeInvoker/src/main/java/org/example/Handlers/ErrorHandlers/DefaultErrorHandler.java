package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.ErrorBase;
import org.example.Suppliers.MethodSupplier;

public class DefaultErrorHandler extends ErrorBase {
    public DefaultErrorHandler() {
        super(DefaultErrorHandler.class);
    }

    @Override
    public void handle(MethodSupplier method, Exception e){
        defaultResponse(e);
    };

    @Override
    public boolean canHandle(Exception err) {
        return err != null;
    }

    @Override
    public String getMessage() {
        return "Something went wrong!";
    }
}
