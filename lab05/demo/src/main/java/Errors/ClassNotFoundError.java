package Errors;

import Thrower.ExceptionThrower;

public class ClassNotFoundError implements ErrorInterface{
    @Override
    public String getMessage() {
        return "Class not found !";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof ClassNotFoundException;
    }

    @Override
    public boolean handle(Exception e, ExceptionThrower target) {
        if(canHandle(e)){
            System.out.println(getMessage());
            logger.logError(getMessage());
            return true;
        }
        return false;
    }
}
