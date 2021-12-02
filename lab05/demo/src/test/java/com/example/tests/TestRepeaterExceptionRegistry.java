package com.example.tests;

import org.example.SafeInvoker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestRepeaterExceptionRegistry extends Mockito {
    SafeInvoker safeInvoker = new SafeInvoker();
    @Test
    public void test_should_check_if_exceptions_are_registered_correctly(){
        safeInvoker.invoke(()->{throw new RuntimeException();});
    }


}
