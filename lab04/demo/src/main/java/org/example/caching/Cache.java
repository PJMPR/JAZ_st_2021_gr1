package org.example.caching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cache {
    private static final Cache instance = new Cache();
    private List<CacheItem> items = new ArrayList<>();

    public static Cache getInstance() {
        return instance;
    }


    public <T> void add(String key, Object item){
        items.add(new CacheItem(key,item));
    }

    public <T> T get(String key, Class<T> clazz){
        Object result = new ArrayList<>();
        for (CacheItem item :
                items) {
            if (item.getKey()==key || clazz.equals(item.getItem().getClass())){
                result = (T) item.getItem();
            }
        }
        return (T) result;
    }

    public Object get(String key){
        Object result = new ArrayList<>();
        for (CacheItem item :
                items) {
            if (item.getKey()==key){
                result = item.getItem();
            }
        }
        return result;
    }
}
