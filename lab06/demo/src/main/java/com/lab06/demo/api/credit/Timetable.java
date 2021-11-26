package com.lab06.demo.api.credit;

import com.lab06.demo.models.Calculation;
import com.lab06.demo.services.CSVService;
import com.lab06.demo.services.CalculationService;
import com.lab06.demo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/credit")
public class Timetable {

    private final TimetableService timetableService;
    private final CSVService csvService;

    @Autowired
    public Timetable(TimetableService timetableService, CSVService csvService) {
        this.timetableService = timetableService;
        this.csvService = csvService;
    }

    @GetMapping(path = "/timetable")
    public ResponseEntity getTimetableJSON(@RequestParam long id) {
        return ResponseEntity.ok().body(timetableService.getTimetable(id));
    }

    @GetMapping(path = "/timetable/file")
    public ResponseEntity<Resource> getTimetableCSV(@RequestParam long id, @RequestParam String file) {
        if (file.equals("CSV")) {
            String filename = "file.csv";
            InputStreamResource inputStreamResource = new InputStreamResource(csvService.load(id));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}