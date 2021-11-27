package com.example.demo.web;

import com.example.demo.business.CreditDataService;
import com.example.demo.business.TimetableService;
import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditDataRestController {

    private final TimetableService timetableService;
    private final CreditDataService creditDataService;


    @Autowired
    public CreditDataRestController(TimetableService timetableService, CreditDataService creditDataService) {
        this.timetableService = timetableService;
        this.creditDataService = creditDataService;
    }

    @PostMapping("/calculations")
    public ResponseEntity<Long> createCreditData(@RequestBody CreditData creditData){
        creditDataService.saveCreditData(creditData);
        return new ResponseEntity<>(creditData.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/calculations")
    public ResponseEntity<List<CreditData>> getAllCreditData(){
        return new ResponseEntity<>(creditDataService.getAllCreditData(), HttpStatus.OK);
    }

    @GetMapping("timetable/{id}")
    public ResponseEntity<?> getTimetableById(@PathVariable Long id, @RequestParam(value="file", required=false) Files file){
        if(file == null) return new ResponseEntity<>(timetableService.getTimetable(id), HttpStatus.OK);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(timetableService.getTimeTableToFile(id, file)));
        switch (file){
            case CSV: return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=out.csv")
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);
            case PDF: return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=out.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
