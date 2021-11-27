package com.example.demo.business;

import com.example.demo.data.entity.Timetable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVCreator{
    public static byte[] toFile(List<Timetable> timetables) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),  CSVFormat.POSTGRESQL_CSV)) {
            for (Timetable timetable : timetables) {
                csvPrinter.printRecord(Arrays.asList(
                        timetable.getNumber(),
                        timetable.getCapital(),
                        timetable.getInterest(),
                        timetable.getFixedFee(),
                        timetable.getCapitalToPay(),
                        timetable.getAmount()
                ));
            }
            csvPrinter.flush();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
