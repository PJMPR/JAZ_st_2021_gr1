package org.example.caching.provider;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DictionaryCacheProvider implements CacheProvider{
    public ArrayList<Dictionary> provide() {
        return readDataFromFile();
    }

    private ArrayList<Dictionary> readDataFromFile() {
        ArrayList<Dictionary> items = new ArrayList<>();
        Path pathToFile = Paths.get("dictionaries.csv");

        try (BufferedReader bufferedReaderr = Files.newBufferedReader(pathToFile)) {
            String line = bufferedReaderr.readLine();
            splitAndAddingToElements(items, bufferedReaderr, line);

        } catch (IOException e) {
            e.getCause();
        }
        return items;
    }

    private void splitAndAddingToElements(ArrayList<Dictionary> items, BufferedReader bufferedReaderr, String line) throws IOException {
        while (line != null) {
            String[] attributes = line.split(",");
            Dictionary item = createDictionaryItem(attributes);
            items.add(item);
            line = bufferedReaderr.readLine();
        }
    }

    private Dictionary createDictionaryItem(String[] data) {
        return new Dictionary(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3], data[4]);
    }
}
