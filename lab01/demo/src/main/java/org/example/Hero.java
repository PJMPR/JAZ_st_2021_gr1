package org.example;

import java.lang.reflect.Method;

public class Hero {
    int age;
    int height;
    int power;
    int womens;
    String name;

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    private int getWomens(){ return womens; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
