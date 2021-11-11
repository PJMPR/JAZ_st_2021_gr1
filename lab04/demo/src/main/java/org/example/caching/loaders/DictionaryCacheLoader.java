package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.providers.DictionaryFileProvider;
import org.example.caching.providers.DictionaryProvider;
import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader implements CacheLoader {
    Cache cache = Cache.getInstance();
    List<DictionaryProvider> providers = List.of(
            new DictionaryFileProvider()
    );

    public void load() {
        List<Dictionary> list = new ArrayList<>();

        for (DictionaryProvider provider : providers) {
            list.addAll(provider.provide());
        }

        cache.add("dictionaries", list);

    }
}
