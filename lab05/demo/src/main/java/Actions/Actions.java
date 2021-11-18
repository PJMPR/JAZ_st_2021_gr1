package Actions;

import Thrower.ExceptionThrower;

public class Actions {

    public static boolean reboot(ExceptionThrower target, int times){
        if(times > 0){
            System.out.println("Rebooting ... ");
            try {
                target.throwException();
                return true;
            } catch (Exception e) {
                wait(1);
                reboot(target,times-1);
            }
        }
        return false;
    }

    public static void wait(int sec){
        try{
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
