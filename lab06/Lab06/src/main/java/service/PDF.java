package service;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;


import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.Installment;
import org.springframework.stereotype.Component;

import static java.awt.Color.*;

@Component
public class PDF {
    private List<Installment> installments;

    public PDF(List<Installment> installments) {
        this.installments = installments;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        cell.setPhrase(new Phrase("Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Interest", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fixed fee", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital to pay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table, TimetableService timetableService, int id) {
        List<Installment> installmentList = timetableService.getTimetable(id).getInstalments();

        installmentList.stream().forEach(installment -> {
            table.addCell(String.valueOf(installment.getNumber()));
            table.addCell(String.valueOf(installment.getCapital()));
            table.addCell(String.valueOf(installment.getInterest()));
            table.addCell(String.valueOf(installment.getFixedFee()));
            table.addCell(String.valueOf(installment.getCapitalToPay()));
            table.addCell(String.valueOf(installment.getAmount()));
        });
    }

    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.BLUE);

        Paragraph p = new Paragraph("Installments schedule", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{2.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, timetableService, id);

        document.add(table);

        document.close();
    }
}
