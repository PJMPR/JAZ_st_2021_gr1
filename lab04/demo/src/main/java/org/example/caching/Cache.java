package org.example.caching;

import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache instance = new Cache();
    private List<CacheItem> cacheItemList = new ArrayList<>();

    public static Cache getInstance(){
        return instance;
    }


    public <T> void add(String key, T item){
        CacheItem cacheItem = new CacheItem(key,item);

        cacheItemList.add(cacheItem);
    }

    public <T> T get(String key, Class<T> clazz){

        return (T) cacheItemList.stream().filter(cacheItem -> Objects.equals(cacheItem.getKey(),key)).findAny().get().getItem();
    }

    public Object get(String key){
        return cacheItemList.stream().filter(cacheItem -> key.equals(cacheItem.getKey())).map(cacheItem -> cacheItem.getItem()).collect(Collectors.toList());
    }
}
