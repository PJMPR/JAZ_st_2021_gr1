package errors;

import java.sql.SQLException;

public class SQLErrorInterface implements ErrorInterface {
    @Override
    public String getMessage() {
        return "Erorr with connection to database";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if (canHandle(err)) {

            System.out.println("Trying to reconecting to database");
            actions.wait(2);

            if (actions.redo(method, 5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }


    @Override
    public boolean canHandle(Exception err) {
        return err instanceof SQLException;
    }

}
