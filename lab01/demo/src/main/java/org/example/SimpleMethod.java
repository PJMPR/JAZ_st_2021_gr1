package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;

public class SimpleMethod {
    Method method;
    public SimpleMethod(Method m) {
        method=m;
    }

    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    public boolean startsWith(String prefix) {
        return method.getName().startsWith(prefix);
    }

    public boolean hasParamsCount(int n) {
        return method.getParameterCount()==n;
    }

    public boolean isVoid() {
        return method.getReturnType().equals(void.class);
    }

    public boolean isGetter()
    {
        return isPublic()
                && (startsWith("get") || startsWith("is"))
                && !isVoid()
                && hasParamsCount(0);
    }
    public boolean isSetter()
    {
        return isPublic()
                && startsWith("set")
                && isVoid()
                && hasParamsCount(1);
    }

    public String fieldName(){
        if(startsWith("get") || startsWith("set"))
            return method.getName().substring(3).toLowerCase(Locale.ROOT);
        if(startsWith("is"))
            return method.getName().substring(2).toLowerCase(Locale.ROOT);
        return "";
    }
}
