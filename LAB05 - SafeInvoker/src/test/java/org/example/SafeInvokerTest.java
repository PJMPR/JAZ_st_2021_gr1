package org.example;

import org.example.Handlers.ErrorBase;
import org.example.Handlers.ErrorHandlers.DefaultErrorHandler;
import org.example.Handlers.ErrorHandlers.FileNotFoundExceptionHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SafeInvokerTest {

    @InjectMocks
    private SafeInvoker safeInvoker;

    @Test
    public void findErrorHandlerShouldReturnErrorHandlerWhenErrorIsOnTheList() {
        ErrorBase errorHandler = safeInvoker.findErrorHandler(new FileNotFoundException());
        assertEquals(FileNotFoundExceptionHandler.class, errorHandler.getClass());
    }


    @Test
    public void findErrorHandlerShouldReturnDefaultErrorHandlerWhenErrorIsNotInTheList() {
        ErrorBase errorHandler = safeInvoker.findErrorHandler(new Exception());
        assertEquals(DefaultErrorHandler.class, errorHandler.getClass());
    }

    @Test
    public void invokeShouldNotThrowErrors() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {throw new Exception();})
        );
    }
}
