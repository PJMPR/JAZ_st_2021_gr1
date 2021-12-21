package org.example.caching;

<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Cache {

    List<CachedItem> items = new ArrayList<>();

    private static volatile Cache instance;

    public static Cache getInstance() {

        Cache result = instance;
        if (result != null) {
            return result;
        }

        synchronized (Cache.class) {
            if (instance == null) {
                instance = new Cache();
            }
            return instance;
        }
    }

    public <T> void add(String key, T item) {
        items.add(new CachedItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) items.stream().filter(cachedItem -> Objects.equals(cachedItem.getKey(), key)).findAny().get().getItem();
    }

    public Object get(String key) {
        return items.stream()
                .filter(cachedItem -> Objects.equals(cachedItem.getKey(), key))
                .map(cachedItem -> cachedItem.getItem())
                .findAny().get();
    }
}
=======
import org.example.model.Dictionary;

import java.util.List;

public class Cache {

    public static Cache getInstance(){
        return new Cache();
    }


    public <T> void add(String key, T item){

    }

    public <T> T get(String key, Class<T> clazz){

        return (T) clazz.cast(new Object());
    }

    public Object get(String key){
        return null;
    }
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
