package errors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public void log(String msg){
        try {
            File file= new File("log.txt");
            file.createNewFile();
            BufferedWriter writer= new BufferedWriter(new FileWriter(file));
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
