package controller;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CSV;
import service.InstallmentService;
import service.PDF;
import service.TimetableService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TimetableControler {
    InstallmentService installmentService;
    TimetableService timetableService;
    service.PDF PDF;
    service.CSV CSV;

    @Autowired
    public TimetableControler(InstallmentService installmentService, TimetableService timetableService, PDF PDF, CSV CSV) {
        this.installmentService = installmentService;
        this.timetableService = timetableService;
        this.PDF = PDF;
        this.CSV = CSV;
    }

    @PostMapping("/credit/calculations")
    public long saveInstallmentData(@RequestBody Timetable timetable) {
        int id = timetableService.insertData(timetable);
        List<Installment> installments = new ArrayList<>(installmentService.calculateInstallments(timetable));
        installments.forEach(installment -> installmentService.saveInstallments(installment));
        return id;
    }

    @GetMapping("credit/timetable")
    public Timetable getTimetable(@RequestParam Integer id) {
        return timetableService.getTimetable(id);
    }

    @GetMapping(value = "/credit/timetable", params = {"id", "file"})
    public void getTimetableInFile(HttpServletResponse response, @RequestParam Integer id, @RequestParam String file) throws IOException, DocumentException {
        if(file.equals("pdf")){
            PDF.getFile(response, id, timetableService);
        }
        else if (file.equals("csv")){
            CSV.getFile(response, id, timetableService);
        }
    }


}
