package Errors;


import Actions.Actions;
import Logger.ErrorLogger;
import Thrower.ExceptionThrower;

public interface ErrorInterface {
    ErrorLogger logger = new ErrorLogger();
    String getMessage();
    boolean canHandle(Exception e);
    boolean handle(Exception e, ExceptionThrower target);

}
