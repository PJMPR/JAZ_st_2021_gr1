package org.example;

import org.example.Handlers.*;
import org.example.Supplier.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<Handler> handlers = List.of(
            new TimeOutHandler(),
            new DataBaseHandler(),
            new FileNotFoundHandler()
    );

    DefaultHandler defaultHandler = new DefaultHandler();

    AtomicBoolean flag = new AtomicBoolean(false);

    public void invoke(Supplier supplier) {
        try {
            supplier.execute();
        } catch (Exception e) {
            handlers.stream()
                    .filter(handler -> handler.canHandle(e))
                    .forEach(handler ->
                flag.set(handler.handle(e, supplier)));
            if(!flag.get())
            {
                defaultHandler.handle(e, supplier);
            }
        }
    }
}
