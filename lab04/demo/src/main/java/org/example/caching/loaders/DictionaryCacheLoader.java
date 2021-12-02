package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.FileLoader.FromFileLoader;
import org.example.caching.FileLoader.Loader;
import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;


public class DictionaryCacheLoader implements CacheLoader{

    Cache cache = Cache.getInstance();

    List<Loader> loaders = List.of(
            new FromFileLoader()
    );

    public void load(){
        List<Dictionary> items = new ArrayList<>();

        for (Loader loader: loaders) {
            items.addAll(loader.provide());
        }

        cache.add("dictionaries", items);
    }


}
