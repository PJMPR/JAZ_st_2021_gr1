package errors;

public interface Erorr {
    Actions actions = new Actions();
    Logger logger = new Logger();
    String getMessage();
    void handle(Exception err, Supplier method);
    boolean canHandle(Exception err);

}