package errors;

import java.util.concurrent.TimeoutException;

public class TimeoutErrorInterface implements ErrorInterface {
    @Override
    public String getMessage() {
        return "Timeout erorr";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if (canHandle(err)) {
            System.out.println("Plese wait we trying to reconnecting ");
            actions.wait(2);
            if (actions.redo(method,5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof TimeoutException;
    }

}
