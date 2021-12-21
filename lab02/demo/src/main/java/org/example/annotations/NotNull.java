package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
<<<<<<< HEAD
import java.lang.reflect.Method;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String message() default "field is null";
    String message2() default "field is empty";
}
=======

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {
    String message() default "field is null";
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
