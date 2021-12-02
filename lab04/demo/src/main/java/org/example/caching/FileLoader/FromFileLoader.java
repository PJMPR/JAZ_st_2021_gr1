package org.example.caching.FileLoader;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FromFileLoader implements Loader{

    @Override
    public ArrayList<Dictionary> provide() {
        return  readDataFromFile();
    }

    private ArrayList<Dictionary> readDataFromFile(){
        ArrayList<Dictionary> elements = new ArrayList<>();
        Path path = Paths.get("C:\\Users\\barte\\Documents\\PJAKT\\OneDrive - Polsko-Japońska Akademia Technik Komputerowych\\JAZ\\GIT\\JAZ_st_2021_gr1\\lab04\\demo\\dictionaries.csv");
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createDictionaryItem(attributes);

                elements.add(item);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }
    private Dictionary createDictionaryItem(String[] attributes)
    {
        return new Dictionary(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), attributes[2], attributes[3], attributes[4]);
    }

}
