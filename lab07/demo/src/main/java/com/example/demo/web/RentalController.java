package com.example.demo.web;

import com.example.demo.business.charts.ChartType;
import com.example.demo.business.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("Rental")
public class RentalController {
    private final RentalService services;

    @Autowired
    public RentalController(RentalService services) {
        this.services = services;
    }

    @GetMapping(value = "/incomeByMonth", params = {"year"})
    public ResponseEntity<?> getIncomeByMonth(@RequestParam String year,
                                              @RequestParam(required = false) ChartType chart){
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(services.getIncomeByMonth(year));
    }

    @GetMapping(value = "/income", params = {"from","to"})
    public ResponseEntity<?> getIncomeFromTo(@RequestParam Date from, @RequestParam Date to,
                                             @RequestParam(required = false) ChartType chart){
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(services.getIncomeFromTo(from, to));
    }
}


