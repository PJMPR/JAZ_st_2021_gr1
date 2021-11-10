package org.example.caching;

import java.util.Date;

public record CacheItem<T>(String dataSet, T data, Date expiration) {
    public String getDataSet() {
        return dataSet;
    }

    public T getData() {
        return data;
    }

    public Date getExpiration() {
        return expiration;
    }
}
