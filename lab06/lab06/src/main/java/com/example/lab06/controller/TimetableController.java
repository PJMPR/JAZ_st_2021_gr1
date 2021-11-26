package com.example.lab06.controller;

import com.example.lab06.func.Calculation;
import com.example.lab06.service.CalculationService;
import com.example.lab06.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimetableController {
    private final CalculationService calculationService;
    private final TimetableService timetableService;

    @Autowired
    public TimetableController(CalculationService calculationService, TimetableService timetableService) {
        this.calculationService = calculationService;
        this.timetableService = timetableService;
    }

    @RequestMapping
    public String Lab06Application() {
        return "It works!";
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation) {
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public ResponseEntity getCalculation(@RequestParam long id) {
        return ResponseEntity.ok().body(calculationService.getCalculation(id));
    }

    @GetMapping(path = "/timetable")
    public ResponseEntity getTimetableJSON(@RequestParam long id) {
        return ResponseEntity.ok().body(timetableService.getTimetable(id));
    }

}
