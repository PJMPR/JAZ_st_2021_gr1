package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.ErrorBase;
import org.example.Suppliers.MethodSupplier;

import java.sql.SQLException;

public class SQLExceptionHandler extends ErrorBase {
    public SQLExceptionHandler() {
        super(SQLExceptionHandler.class);
    }

    @Override
    public void handle(MethodSupplier method, Exception e) {
        if(retry(method, 5, 5000)){
            defaultResponse(e);
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof SQLException;
    }

    @Override
    public String getMessage() {
        return "Database connection has been interrupted";
    }
}
