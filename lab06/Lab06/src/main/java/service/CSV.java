package service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



import controller.Installment;

import org.springframework.stereotype.Component;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


@Component
public class CSV {

    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Installments_id=" + id + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Installment> installmentList = timetableService.getTimetable(id).getInstalments();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Number", "Capital", "capital to pay", "fixed fee", "interest", "amount"};
        String[] nameMapping = {"number", "capital", "capitalToPay", "fixedFee", "interest", "amount"};

        csvWriter.writeHeader(csvHeader);

        for (Installment installment : installmentList) {
            csvWriter.write(installment, nameMapping);
        }

        csvWriter.close();
    }
}
