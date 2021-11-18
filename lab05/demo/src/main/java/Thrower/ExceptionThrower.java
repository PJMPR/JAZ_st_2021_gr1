package Thrower;

@FunctionalInterface
public interface ExceptionThrower {
    void throwException() throws Exception;
}
