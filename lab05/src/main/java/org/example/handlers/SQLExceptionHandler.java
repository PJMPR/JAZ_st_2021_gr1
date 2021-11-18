package org.example.handlers;

import org.example.ActionCommands;
import org.example.Supplier;

public class SQLExceptionHandler implements ExceptionHandler {
    ActionCommands actionCommands = new ActionCommands();

    @Override
    public String getMessage() {
        return "Cannot connect to database. Check internet connection";
    }

    @Override
    public void handle(Exception error, Supplier method) {
        if(canHandle(error)){
            System.out.println("Cannot connect to database. Trying to connect");
            actionCommands.sleep(5);
            if(actionCommands.restart(method, 5)){
                System.out.println(getMessage());
                logger.log(getMessage());
            }
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return false;
    }
}

