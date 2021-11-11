package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ItemFromFileProvider implements DictionaryProvider {

    @Override
    public List<Dictionary> listOfProvidedItems() {
        return getItemsFromFile();
    }

    private static List<Dictionary> getItemsFromFile() {
        List<Dictionary> items = new ArrayList<>();
        Path filePath = Paths.get("C:\\Users\\Mikołaj\\Documents\\GitHub\\JAZ_st_2021_gr1\\lab04\\dictionaries.csv");

        try(BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line = bufferedReader.readLine();

            while (line != null){
                String[] attributes = line.split(",");
                Dictionary item = createItem(attributes);

                items.add(item);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static Dictionary createItem(String[] attributes) {
        return new Dictionary(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]),attributes[2],attributes[3],attributes[4]);
    }


}
