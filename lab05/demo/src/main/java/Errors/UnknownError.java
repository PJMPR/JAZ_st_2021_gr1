package Errors;

import Thrower.ExceptionThrower;

public class UnknownError implements ErrorInterface{
    @Override
    public String getMessage() {
        return "Unknown error !";
    }

    @Override
    public boolean canHandle(Exception e) {
        return true;
    }

    @Override
    public boolean handle(Exception e, ExceptionThrower target) {
        System.out.println(getMessage());
        logger.logError(getMessage(),e);
        return true;
    }
}
