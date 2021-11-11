package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.provider.CacheProvider;
import org.example.caching.provider.DictionaryCacheProvider;
import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader {
    Cache cache = Cache.getInstance();
    List<CacheProvider> providers = List.of(new DictionaryCacheProvider());

    public void load() {
        List<Dictionary> items = new ArrayList<>();
        providers.stream().forEach(cacheProvider -> items.addAll(cacheProvider.provide()));
        cache.add("dictionaries", items);
    }
}
