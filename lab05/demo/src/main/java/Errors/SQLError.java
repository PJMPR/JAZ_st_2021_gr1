package Errors;

import Actions.Actions;
import Thrower.ExceptionThrower;

import java.sql.SQLException;

public class SQLError implements ErrorInterface{
    @Override
    public String getMessage() {
        return "Connection to database lost !";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof SQLException;
    }

    @Override
    public boolean handle(Exception e, ExceptionThrower target) {
        if(canHandle(e)){
            System.out.println("Reconnecting to Database ...");
            Actions.wait(5);
            if(Actions.reboot(target,2)){
                return true;
            }
            System.out.println(getMessage());
            logger.logError(getMessage());
        }
        return false;
    }
}
