package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.providers.DictionaryFileProvider;
import org.example.caching.providers.DictionaryProvider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryCacheLoader implements CacheLoader {
    private final Cache cache;
    private final List<DictionaryProvider> dictionaryProviders;

    public DictionaryCacheLoader(){
        cache = Cache.getInstance();
        dictionaryProviders = List.of(
                new DictionaryFileProvider("dictionaries.csv", ",")
        );
    }

    public void load(){
        cache.add("dictionaries", dictionaryProviders.stream()
                        .map(DictionaryProvider::provide)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
    }
}
