package org.example.Handlers;

import org.example.Supplier.Supplier;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class DataBaseHandler implements Handler{
    @Override
    public String getMessage() {
        return "Connection with data base time out";
    }
    @Override
    public boolean handle(Exception e, Supplier method) {
        if(canHandle(e))
        {
            action.wait(3);
            if (action.redo(method, 4))
            {
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
        return e instanceof SQLException;
    }
}
