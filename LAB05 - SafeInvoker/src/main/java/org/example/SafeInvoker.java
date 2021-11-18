package org.example;

import org.example.Handlers.ErrorBase;
import org.example.Handlers.ErrorHandlers.DefaultErrorHandler;
import org.example.Handlers.ErrorHandlers.FileNotFoundExceptionHandler;
import org.example.Handlers.ErrorHandlers.IOExceptionHandler;
import org.example.Handlers.ErrorHandlers.SQLExceptionHandler;
import org.example.Suppliers.MethodSupplier;

import java.util.List;

public class SafeInvoker {
    private final List<ErrorBase> errorHandlers = List.of(
            new FileNotFoundExceptionHandler(),
            new IOExceptionHandler(),
            new SQLExceptionHandler()
    );

    public ErrorBase findErrorHandler(Exception e){
        return errorHandlers.stream()
                .filter(errorHandler -> errorHandler.canHandle(e))
                .findAny().orElse(new DefaultErrorHandler());
    }

    public void invoke(MethodSupplier methodSupplier){
        try{
            methodSupplier.execute();
        }catch (Exception e){
            findErrorHandler(e).handle(methodSupplier, e);
        }
    }
}
