package com.lab08.dbapp.hepler;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TheMovieDBJSON {
    JSONParser parser = new JSONParser();

    public Iterable<Integer> getAllMoviesIds(String filename) throws Exception {

        List<Integer> result = new ArrayList<Integer>();
        try {

            File file = new File(filename);

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                JSONObject jsonObject = (JSONObject) parser.parse(sc.nextLine());
                result.add((int) jsonObject.get("id"));
            }

            sc.close();
        } catch (Exception e) {

            e.getStackTrace();
            return null;
        }
        return result;

    }

}
