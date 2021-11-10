package org.example.caching.readers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private Scanner scanner;

    public FileHandler(String filePath) {
        try {
            scanner = new Scanner(Paths.get(filePath).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> splitBy(String delimiter) {
            List<String[]> columns = new ArrayList<>();
            while (scanner.hasNext()) {
                columns.add(scanner.nextLine().split(delimiter));
            }
            return columns;
    }
}
