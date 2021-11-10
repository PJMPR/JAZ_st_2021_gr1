package org.example.caching;

class SafeCaster{
    public static <T,E> T cast(E obj, Class<T> clazz){
        T result = null;
        try{
            if(obj != null) result = clazz.cast(obj);
            return result;
        } catch (ClassCastException ex){
            ex.printStackTrace();
        }
        return null;
    }
}