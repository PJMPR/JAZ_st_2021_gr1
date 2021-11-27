package org.example.caching.providers;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFileProvider implements DictionaryProvider {

    public ArrayList<Dictionary> provide() {
        return readDataFromFile();
    }
    private ArrayList<Dictionary> readDataFromFile(){
        ArrayList<Dictionary> data = new ArrayList<>();
        Path path = Paths.get("../dictionaries.csv");
            String line = "";
            try {
                BufferedReader reader = Files.newBufferedReader(path);
                line = reader.readLine();
                while ( line!=null) {
                    String[] val = line.split(",");
                    Dictionary item = createDictionaryItem(val);
                    data.add(item);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        private Dictionary createDictionaryItem(String[] data){
            return new Dictionary(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2],data[3],data[4]);
        }
    }

