package org.example.tests;

import org.example.SafeInvoker;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class TestSafeInvoker {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void SafeInvokerShouldNotThrowClassNotFoundException(){
        safeInvoker.invoke(() -> {throw new ClassNotFoundException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowFileNotFoundException(){
        safeInvoker.invoke(() -> {throw new FileNotFoundException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowSQLExceptionHandler(){
        safeInvoker.invoke(() -> {throw new SQLException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowTimeoutException(){
        safeInvoker.invoke(() -> {throw new TimeoutException();});
    }

}
