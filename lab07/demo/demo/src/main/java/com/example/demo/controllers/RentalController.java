package com.example.demo.controllers;

import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.Services.ChartsType;
import com.example.demo.Services.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @GetMapping
    @RequestMapping("/incomeByMonth.jpg/{chart}/{year}")
    public ResponseEntity getIncomeByMonth(@PathVariable int year, @PathVariable String chart) throws IOException {
        switch (chart) {
            case "linear":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.rentalChart(
                                ChartsType.LINEAR,
                                "Income by month",
                                "Months",
                                "Income",
                                rentalService.getIncomeByMonth(year)));
            case "bar":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.rentalChart(
                                ChartsType.BAR,
                                "Income by month",
                                "Months",
                                "Income",
                                rentalService.getIncomeByMonth(year)));
            case "pie":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.rentalChart(
                                ChartsType.PIE,
                                "Income by month",
                                "",
                                "",
                                rentalService.getIncomeByMonth(year)));
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
