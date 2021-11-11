package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;
import org.example.model.DictionaryProvider;
import org.example.model.ItemFromFileProvider;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader {
    Cache cache = Cache.getInstance();

    List<DictionaryProvider> providers =List.of(
            new ItemFromFileProvider()
    );

    public void load(){
        List<Dictionary> items = new ArrayList<>();

        for(DictionaryProvider dictionaryProvider : providers){
            items.addAll(dictionaryProvider.listOfProvidedItems());
        }

        cache.add("dictionaries",items);
    }
}
