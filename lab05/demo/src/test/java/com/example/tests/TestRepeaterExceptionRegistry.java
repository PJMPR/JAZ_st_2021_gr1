package com.example.tests;

import org.example.SafeInvoker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.security.KeyException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class TestRepeaterExceptionRegistry extends Mockito {
    SafeInvoker safeInvoker = new SafeInvoker();
//    @Test
//    public void test_should_check_if_exceptions_are_registered_correctly(){
//        safeInvoker.invoke(()->{throw new RuntimeException();});
//    }
//
//    @Test
//    public void CheckIfFileNotFoundExceptionIsHandled(){
//        safeInvoker.invoke(()->{throw new FileNotFoundException();});
//    }
//
    @Test
    public void CheckIfDefaultErrorIsHandled(){
        safeInvoker.invoke(()->{throw new KeyException();});
    }

    @Test
    public void CheckIfSQLExceptionIsHandled(){
        safeInvoker.invoke(()->{throw new SQLException();});
    }

    @Test
    public void CheckIfTimeoutExceptionIsHandled(){
        safeInvoker.invoke(()->{throw new TimeoutException();});
    }

}
