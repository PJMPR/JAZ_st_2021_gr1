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
public class Calculations {

    private final CalculationService calculationService;

    @Autowired
    public Calculations(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation) {
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public ResponseEntity getCalculation(@RequestParam long id) {
        return ResponseEntity.ok().body(calculationService.getCalculation(id));
    }

}
