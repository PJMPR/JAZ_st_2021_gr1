package com.example.demo.business.converters;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTimestampConverter implements Converter<String, Timestamp> {
    @Override
    public Timestamp convert(String year) {
            try {
                Date parsedDate = new SimpleDateFormat("yyyy").parse(year);
                return new Timestamp(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
    }
}