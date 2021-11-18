package org.example;

import org.example.handlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    DefaultExceptionHandler defaultErrorHandler = new DefaultExceptionHandler();

    List<ExceptionHandler> exceptionHandlerList = new ArrayList<>(List.of(
            new ClassNotFoundExceptionHandler(),
            new FileNotFoundExceptionHandler(),
            new SQLExceptionHandler(),
            new TimeoutExceptionHandler()
    ));

    public void invoke(Supplier method){
        try{
            method.execute();
        } catch (Exception e) {
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            exceptionHandlerList.stream()
                    .filter(errorHandler -> errorHandler.canHandle(e))
                    .forEach(errorHandler -> {
                        errorHandler.handle(e,method);
                        wasHandled.set(true);
                    });
            if(!wasHandled.get()){
                defaultErrorHandler.handle(e,method);
            }
        }
    }
}
