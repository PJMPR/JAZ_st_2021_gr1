package org.example;

public class ActionCommands {
    public void sleep(int second){
        try{
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean restart(Supplier method, int counter){
        if(counter > 0){
            System.out.println("Attempts of reconnection left: " + counter);
            try{
                method.execute();
                return true;
            } catch (Exception e) {
                sleep(10);
                restart(method, counter-1);
            }
        }
        return false;
    }
}

