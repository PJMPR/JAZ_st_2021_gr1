package org.example;

public class Application {


    public static void main(String[] args){
        ObjectPropertyProvider opp=new ObjectPropertyProvider();
        System.out.println(opp.getPublicGetters(Człowiek.class));
        System.out.println(opp.getPublicSetters(Człowiek.class));
        System.out.println(opp.getFieldsForPublicProperties(Człowiek.class));

    }
}
