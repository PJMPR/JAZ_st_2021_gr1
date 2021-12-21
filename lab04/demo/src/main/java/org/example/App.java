package org.example;

<<<<<<< HEAD
import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws IOException {

        int number = 10;
        Double d = new SafeCaster().cast(number, Double.class);
        //if(d!=null)
        System.out.println(d);
        Object tst = 1;
        Integer s = new SafeCaster().cast(tst, Integer.class);
        System.out.println(s);

        Path pathToFile = Paths.get("dictionaries.csv");

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                Arrays.stream(attributes).forEach(System.out::println);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SafeCaster {

    public <T, E> T cast(E obj, Class<T> clazz) {

        T result = null;
        try {
            if (obj != null) result = clazz.cast(obj);
            return result;
        } catch (ClassCastException ex) {
            //ex.printStackTrace();
        }
        return null;
    }
=======
public class App {

    public static void main(String[] args){

        int number = 10;
        double d1 = number;
        Double d = new SafeCaster().cast(number, Double.class);
        //if(d!=null)
        System.out.println(d);
        Object tst = "test";
        String s = new SafeCaster().cast(tst, String.class);
        System.out.println(s);

    }



}
class SafeCaster{

    public <T,E> T cast(E obj, Class<T> clazz){

        T result = null;
        try{
            if(obj != null) result = clazz.cast(obj);
        return result;
        }

            catch (ClassCastException ex){
                //ex.printStackTrace();
            }
        return null;
    }

>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
}