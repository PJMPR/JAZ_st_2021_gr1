package Errors;

import Thrower.ExceptionThrower;

import java.io.FileNotFoundException;

public class FileNotFoundError implements ErrorInterface{
    @Override
    public String getMessage() {
        return "File not found, check file path !";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof FileNotFoundException;
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
