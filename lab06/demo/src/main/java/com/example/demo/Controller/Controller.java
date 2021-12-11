package com.example.demo.Controller;

import com.example.demo.Exporters.CSV;
import com.example.demo.Exporters.PDF;
import com.example.demo.PKO.Installment;
import com.example.demo.PKO.Timetable;
import com.example.demo.Service.InstallmentService;
import com.example.demo.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    InstallmentService installmentService;
    TimetableService timetableService;
    PDF pdf;
    CSV csv;

    @Autowired
    public Controller(InstallmentService installmentService, TimetableService timetableService, PDF pdf, CSV csv) {
        this.installmentService = installmentService;
        this.timetableService = timetableService;
        this.pdf = pdf;
        this.csv = csv;
    }

    @GetMapping("/credit/timetable")
    public Timetable getTimetable(@RequestParam int id) {
        return timetableService.getTimetable(id);
    }

    @PostMapping("/credit/calculations")
    public int calculation(@RequestBody Timetable timetable) {
        int id = timetableService.saveTimetable(timetable);
        List<Installment> installments = new ArrayList<>(installmentService.calculateInstallment(timetable));
        installments.forEach(installment -> installmentService.saveInstallments(installment));
        return id;
    }

    @GetMapping(value = "/credit/timetable", params = {"id", "file"})
    public void getTimetableInFile(HttpServletResponse response, @RequestParam Integer id, @RequestParam String file) throws IOException {
        switch (file) {
            case "pdf" -> pdf.getFile(response, id, timetableService);
            case "csv" -> csv.getFile(response, id, timetableService);
        }
    }
}