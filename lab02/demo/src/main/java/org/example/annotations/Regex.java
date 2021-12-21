package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

<<<<<<< HEAD
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Regex {
    String pattern() default "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    String message() default "regex error";

}
=======
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Regex {
    String pattern();
    String message() default "regex error";
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
