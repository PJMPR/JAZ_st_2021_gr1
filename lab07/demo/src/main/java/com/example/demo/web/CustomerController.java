package com.example.demo.web;


import com.example.demo.business.charts.ChartType;
import com.example.demo.business.services.CustomerService;
import com.example.demo.data.projections.Customer.CustomerBySpentMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getTenCustomersBySpentMoney(@RequestParam(required = false) ChartType chart){
        List<CustomerBySpentMoney> list = services.getCustomersBySpentMoneyWithLimit(10);
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/ranking/byWatchedMovies")
    public ResponseEntity<?> getTenCustomersByWatchedMovies(@RequestParam(required = false) ChartType chart){
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(services.getCustomersByWatchedMoviesWithLimit(10));
    }

    @GetMapping(value = "/activity/rentMoviesByMonth", params = "year")
    public ResponseEntity<?> getRentMoviesByMonth(@RequestParam String year,
                                                  @RequestParam(required = false) ChartType chart){
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(services.getRentMoviesByMonth(year));
    }

    @GetMapping(value = "/activity/rentMoviesByMonth", params = {"customerId"})
    public ResponseEntity<?> getCustomerRentMoviesByMonth(@RequestParam Integer customerId,
                                                          @RequestParam(required = false) ChartType chart){
        if(chart != null) System.out.println(chart);
        return ResponseEntity.ok(services.getCustomerRentMoviesByMonth(customerId));
    }
}

