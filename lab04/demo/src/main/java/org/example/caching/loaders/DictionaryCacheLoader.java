package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.provider.ItemsFileProvider;
import org.example.model.Dictionary;
import java.util.List;

public class DictionaryCacheLoader implements CacheLoader {
    private Cache cache = Cache.getInstance();

    @Override
    public void load() {
        ItemsFileProvider provider = new ItemsFileProvider();
        List<Dictionary> listOfItems = provider.provideListOfItems();
        String fileName = provider.fileName();

        listOfItems.stream()
                .forEach(listofitem -> cache.add(fileName,listofitem));
    }
}
