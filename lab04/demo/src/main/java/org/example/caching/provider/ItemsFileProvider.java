package org.example.caching.provider;

import org.example.model.Dictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ItemsFileProvider implements DictionaryProvider {

    @Override
    public List<Dictionary> provideListOfItems() {
        String filePath = "dictionaries.csv";
        return readItemsFromCSV(filePath);
    }

    private static List<Dictionary> readItemsFromCSV(String filePath) {
        List<Dictionary> dictionaryList = new ArrayList<>();
        Path pathToFile = Paths.get(filePath);

        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToFile)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createItem(attributes);

                dictionaryList.add(item);

                line = bufferedReader.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return dictionaryList;
    }

    private static Dictionary createItem(String[] attribute) {
        int id = Integer.parseInt(attribute[0]);
        int intKey = Integer.parseInt(attribute[1]);
        String stringKey = attribute[2];
        String value = attribute[3];
        String dictionaryName = attribute[4];

        return new Dictionary(id, intKey, stringKey, value, dictionaryName);
    }

    @Override
    public String fileName() {
        File file = new File("dictionaries.csv");
        return file.getName().replaceFirst("[.][^.]+$", "");
    }
}