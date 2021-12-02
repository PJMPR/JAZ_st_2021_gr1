package org.example.Actions;

import org.example.Supplier.Supplier;

public class Actions {
    public void wait(int sceonds)
    {
        try {
            Thread.sleep(sceonds*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean redo(Supplier method, int times) {
        if (times > 0) {
            System.out.println("Attempts left: "+times);
            try {
                method.execute();
                return true;
            } catch (Exception e) {
                wait(2);
                redo(method, times-1 );
            }
        }
        return false;
    }
}
