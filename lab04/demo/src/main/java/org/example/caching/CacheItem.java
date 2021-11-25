package org.example.caching;

public class CacheItem<T> {
    private String key;
    private Object item;


    public CacheItem(String key,T item){
        this.item = item;
        this.key = key;
    }

    public Object getItem(){
        return this.item;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
}
