package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    private Optional<CachedItem> getCachedItemStream(String key ) {
        return items
                .stream()
                .filter(cachedItem -> Objects.equals(cachedItem.getKey(), key))
                .findAny();
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) getCachedItemStream(key)
                .get()
                .getItem();
    }

    public Object get(String key) {
        return getCachedItemStream(key)
                .map(cachedItem -> cachedItem.getItem())
                .get();
    }
}
