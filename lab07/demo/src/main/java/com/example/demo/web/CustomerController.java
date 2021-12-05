package com.example.demo.web;

import com.example.demo.business.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.sql.Date;

@RestController
@RequestMapping("Customers")
public class CustomerController {

    private final CustomerService services;

    @Autowired
    public CustomerController(CustomerService services) {
        this.services = services;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomerPayments(@PathVariable("id") int id){
        return ResponseEntity.ok(services.getCustomerPayments(id));
    }

    @GetMapping("/ranking/bySpentMoney")
    public ResponseEntity<?> getTenCustomersBySpentMoney(){
        return ResponseEntity.ok(services.getCustomersBySpentMoneyWithLimit(10));
    }

    @GetMapping("/ranking/byWatchedMovies")
    public ResponseEntity<?> getTenCustomersByWatchedMovies(){
        return ResponseEntity.ok(services.getCustomersByWatchedMoviesWithLimit(10));
    }

    @GetMapping(value = "/activity/rentMoviesByMonth", params = {"year"})
    public ResponseEntity<?> getRentMoviesByMonth(@RequestParam String year){
        return ResponseEntity.ok(services.getRentMoviesByMonth(year));
    }

    @GetMapping(value = "/activity/rentMoviesByMonth", params = {"customerId"})
    public ResponseEntity<?> getCustomerRentMoviesByMonth(@RequestParam Integer customerId){
        return ResponseEntity.ok(services.getCustomerRentMoviesByMonth(customerId));
    }


}

