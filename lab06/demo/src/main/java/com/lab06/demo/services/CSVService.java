package com.lab06.demo.services;

import com.lab06.demo.models.Timetable;
import com.lab06.demo.repositories.TimetableRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVService {
    TimetableRepository timetableRepository;

    @Autowired
    public CSVService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public ByteArrayInputStream load(long id) {
        List<Timetable> timetableList = timetableRepository.findAllById(id);

        return toCSV(timetableList);
    }

    public ByteArrayInputStream toCSV(List<Timetable> timetables) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Timetable timetable : timetables) {
                List<Number> data = Arrays.asList(
                        timetable.getNumber(),
                        timetable.getCapital(),
                        timetable.getInterest(),
                        timetable.getFixedFee(),
                        timetable.getCapitalToPay(),
                        timetable.getAmount()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
