package org.example.caching;

public class CacheItem<T> {
    private T item;
    private String key;

    public CacheItem(String key, T item) {
        this.key = key;
        this.item = item;
    }

    public String getKey() {
        return key;
    }

    public T getItem() {
        return item;
    }
}
