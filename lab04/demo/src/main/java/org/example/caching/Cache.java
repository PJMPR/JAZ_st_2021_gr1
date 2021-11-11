package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cache {

    List<CacheItem> items = new ArrayList<>();

    public static volatile Cache instance;

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
//        return new Cache();
    }


    public <T> void add(String key, T item) {
        items.add(new CacheItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) (T) items.stream().filter(cachedItem -> Objects.equals(cachedItem.getKey(), key)).findAny().get().getItem();
    }

    public Object get(String key) {
        return items.stream().filter(cacheItem -> Objects.equals(cacheItem.getKey(), key)).map(cacheItem -> cacheItem.getItem()).findAny().get();
    }
}
