package errors;

public class DefaultErrorHandler implements Erorr{

    @Override
    public String getMessage() {
        return "Not recognized error";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        System.out.println(getMessage());

    }

    @Override
    public boolean canHandle(Exception err) {
        return true;
    }
}
