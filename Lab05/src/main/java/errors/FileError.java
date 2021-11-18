package errors;

import java.io.FileNotFoundException;

public class FileError implements ErrorInterface {
    @Override
    public String getMessage() {
        return "Not found File";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if(canHandle(err)){
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }
}
