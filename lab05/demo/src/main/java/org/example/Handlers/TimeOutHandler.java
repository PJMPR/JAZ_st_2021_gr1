package org.example.Handlers;

import org.example.Actions.Actions;
import org.example.Supplier.Supplier;

import java.util.concurrent.TimeoutException;

public class TimeOutHandler implements Handler {
    @Override
    public String getMessage() {
        return "Time Out";
    }

    @Override
    public boolean handle(Exception e, Supplier method) {
        if (canHandle(e)) {
            action.wait(3);

            if (action.redo(method, 4)) {
                return true;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
            return true;
        }
        return false;
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof TimeoutException;
    }
}
