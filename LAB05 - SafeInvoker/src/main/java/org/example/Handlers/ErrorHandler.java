package org.example.Handlers;

import org.example.Suppliers.MethodSupplier;

public interface ErrorHandler {
    void handle(MethodSupplier method, Exception e);
    boolean canHandle(Exception err);
    String getMessage();
}
