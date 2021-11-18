package org.example.handlers;

import org.example.ActionCommands;
import org.example.Supplier;
import java.sql.SQLException;

public class TimeoutExceptionHandler implements ExceptionHandler {

    ActionCommands actionCommands = new ActionCommands();

    @Override
    public String getMessage() {
        return "Connection timed out. Please check your internet connection";
    }

    @Override
    public void handle(Exception error, Supplier method) {
        if(canHandle(error)){
            System.out.println("Connection timed out. Trying to reconnect");
            actionCommands.sleep(10);
            if(actionCommands.restart(method,5)){
                System.out.println(getMessage());
                logger.log(getMessage());
            }
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return error instanceof SQLException;
    }
}

