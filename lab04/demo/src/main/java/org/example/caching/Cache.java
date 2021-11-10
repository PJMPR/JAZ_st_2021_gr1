package org.example.caching;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cache {
    private static final Cache instance = new Cache();
    List<CacheItem> cacheItems = new ArrayList<>();

    public synchronized static Cache getInstance(){
        return instance;
    }

    public synchronized <T> void add(String key, T item){
        if( key != null && item != null)
            cacheItems.add(new CacheItem(key, item, new Date()));
    }

    public synchronized <T> T get(String key, Class<T> clazz){
        return SafeCaster.cast(get(key), clazz);
    }

    public synchronized Object get(String key){
        return cacheItems.stream()
                .filter(cacheItem -> cacheItem.getDataSet().equals(key))
                .map(CacheItem::getData)
                .parallel()
                .findAny()
                .orElse(null);
    }

    public synchronized void clear(){
        cacheItems.clear();
    }
}
