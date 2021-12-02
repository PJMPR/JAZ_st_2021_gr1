package org.example.Handlers;

import org.example.Actions.Actions;
import org.example.Logger.ExceptionLogger;
import org.example.Supplier.Supplier;

public interface Handler {
    ExceptionLogger logger = new ExceptionLogger();
    Actions action = new Actions();
    String getMessage();
    void handle(Exception e, Supplier method);
    boolean canHandle(Exception e);
}
