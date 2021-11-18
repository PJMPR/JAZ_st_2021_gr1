


import errors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {

    List<ErrorInterface> errorHandlersList = new ArrayList<>(List.of(
            new DefaultErrorInterfaceHandler(),
            new FileError(),
            new SQLErrorInterface(),
            new TimeoutErrorInterface()

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
