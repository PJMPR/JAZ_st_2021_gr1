package Errors;

import Actions.Actions;
import Thrower.ExceptionThrower;

import java.util.concurrent.TimeoutException;

public class TimeOutError implements ErrorInterface{
    @Override
    public String getMessage() {
        return "Connection timed out, check your internet connection !";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof TimeoutException;
    }

    @Override
    public boolean handle(Exception e, ExceptionThrower target) {
        if(canHandle(e)){
            System.out.println("Reconnecting ...");
            Actions.wait(5);
            if(Actions.reboot(target,1)){
                return true;
            }
            System.out.println(getMessage());
            logger.logError(getMessage());
        }
        return false;
    }
}
