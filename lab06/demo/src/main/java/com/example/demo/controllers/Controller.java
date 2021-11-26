package com.example.demo.controllers;

import com.example.demo.entities.Calculation;
import com.example.demo.services.CalculationService;
import com.example.demo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/credit")
public class Controller {

    private final CalculationService calculationService;
    private final TimetableService timetableService;

    @Autowired
    public Controller(CalculationService calculationService, TimetableService timetableService) {
        this.calculationService = calculationService;
        this.timetableService = timetableService;
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation) {
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public ResponseEntity getCalculation(@RequestParam long id) {
        return ResponseEntity.ok().body(calculationService.getCalculation(id));
    }

    @GetMapping(path = "/timetable/{id}")
    public ResponseEntity getTimetableJSON(@PathVariable long id) {
        return ResponseEntity.ok().body(timetableService.getTimetable(id));
    }
}
