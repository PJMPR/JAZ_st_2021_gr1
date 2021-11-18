package SafeInvoker;

import Errors.*;
import Errors.UnknownError;
import Thrower.ExceptionThrower;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<ErrorInterface> errorList = new ArrayList<>(List.of(
            new ClassNotFoundError(),
            new FileNotFoundError(),
            new SQLError(),
            new TimeOutError()
    ));

    UnknownError unknownError = new UnknownError();

    public void invoke(ExceptionThrower target){
        String log4jPath = "src/main/java/logs/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        try {
            target.throwException();
        } catch (Exception e) {
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            errorList.stream()
                    .filter(errorInterface -> errorInterface.canHandle(e))
                    .forEach(errorInterface -> {
                        errorInterface.handle(e,target);
                        wasHandled.set(true);
                    });
            if(!wasHandled.get()) unknownError.handle(e,target);
        }
    }
}
