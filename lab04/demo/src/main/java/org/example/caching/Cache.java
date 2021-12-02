package org.example.caching;

import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cache {

    List<CachedItem> list = new ArrayList<>();

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
        list.add(new CachedItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz) {

        return (T) list.stream().filter(item -> item.getKey().equals(key)).findAny().get().getValue();
    }

    public Object get(String key) {
        return list.stream()
                .filter(cachedItem -> Objects.equals(cachedItem.getKey(), key))
                .map(cachedItem -> cachedItem.getValue())
                .findAny().get();
    }
}
