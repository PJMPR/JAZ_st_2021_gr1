package Handlers;

import Supplier.Supplier;
import actions.Actions;
import logger.Logger;

public interface ErrorHandler {
    Actions actions = new Actions();
    Logger logger = new Logger();
    String getMessage();
    void handle(Exception err, Supplier method);
    boolean canHandle(Exception err);

}