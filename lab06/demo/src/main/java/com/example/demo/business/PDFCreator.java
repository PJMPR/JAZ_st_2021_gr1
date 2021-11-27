package com.example.demo.business;

import com.example.demo.data.entity.Timetable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PDFCreator {
    private static PdfPTable table;

    public static byte[] toFile(List<Timetable> timetables) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            timetables.forEach(PDFCreator::addCellValue);
            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addCellValue(Timetable timetable){
        String[] numbers = timetable.toString().replaceAll("[^\\d.,]+","").split(",");
        if(table == null) table = new PdfPTable(numbers.length);
        for (String str: numbers) {
                table.addCell(str);
        }
    }
}
