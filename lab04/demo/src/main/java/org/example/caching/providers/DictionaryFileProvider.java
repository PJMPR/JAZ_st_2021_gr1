package org.example.caching.providers;

import org.example.caching.readers.FileHandler;
import org.example.model.Dictionary;

import java.util.List;
import java.util.stream.Collectors;

public record DictionaryFileProvider(String filePath, String delimiter) implements DictionaryProvider {
    @Override
    public List<Dictionary> provide() {
        return new FileHandler(filePath).splitBy(delimiter).stream()
                .map(arr -> new Dictionary(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), arr[2], arr[3], arr[4]))
                .collect(Collectors.toList());
    }
}
