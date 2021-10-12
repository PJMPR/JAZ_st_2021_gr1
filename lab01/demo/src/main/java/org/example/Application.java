package org.example;

public class Application {

    public static void main(String[] args){
        ObjectPropertyProvider test= new ObjectPropertyProvider();
        for (int i = 0; i < test.getPublicGetters(Hero.class).size(); i++) {
            System.out.println(test.getPublicGetters(Hero.class).get(i));
        }
    }
}
