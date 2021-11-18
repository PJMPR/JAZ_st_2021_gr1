import errors.*;
import errors.Erorr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<Erorr> errorHandlersList = new ArrayList<>(List.of(
            new DefaultErrorHandler(),
            new FileError(),
            new SQLError(),
            new TimeoutError()
    ));

    public void invoke(Supplier method){

        try {
            method.execute();
        } catch (Exception err){
           AtomicBoolean wasHandled = new AtomicBoolean(false);
            errorHandlersList.stream()
                    .filter(errorHandler -> errorHandler.canHandle(err))
                    .forEach(errorHandler -> wasHandled.set(true));
        }

    }
}
