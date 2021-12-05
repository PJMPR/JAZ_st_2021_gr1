package com.example.demo.business.parsers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTimestamp {
    public static Timestamp parse(String year){
        try {
            Date parsedDate = new SimpleDateFormat("yyyy").parse(year);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
