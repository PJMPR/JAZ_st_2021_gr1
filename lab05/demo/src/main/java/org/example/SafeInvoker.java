package org.example;

import org.example.Handlers.Handler;
import org.example.Handlers.TimeOutHandler;
import org.example.Supplier.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SafeInvoker {
    List<Handler> handlers = List.of(
            new TimeOutHandler()
    );

    public void invoke(Supplier supplier) {
        try {
            supplier.execute();
        } catch (Exception e) {
            handlers.forEach(handler -> handler.handle(e, supplier));

        }
    }
}
