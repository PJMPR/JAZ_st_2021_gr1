package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
<<<<<<< HEAD
import java.lang.reflect.Type;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
int min() default 0;
int max() default 10;

String message() default "number is out of range [0,10]";
}
=======

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int max();
    int min();
    String message() default "number is out of range [%d,%d]";
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
