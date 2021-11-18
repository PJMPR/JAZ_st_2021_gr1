package org.example.handlers;

import org.example.Logger;
import org.example.Supplier;

public interface ExceptionHandler {
    Logger logger = new Logger();
    String getMessage();
    void handle(Exception error, Supplier method);
    boolean canHandle(Exception error);
}

