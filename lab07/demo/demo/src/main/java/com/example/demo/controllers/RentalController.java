package com.example.demo.controllers;

import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.Services.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rental")
@AllArgsConstructor
public class RentalController {
    RentalService rentalService;

    @GetMapping("incomeByMonth/{year}")
    public ResponseEntity<List<RentalDto>> getIncomeByMonth(@PathVariable int year) {
        return ResponseEntity.ok(rentalService.getIncomeByMonth(year));
    }
}
