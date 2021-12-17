package com.example.demo.controllers;

import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.Services.CustomerService;
import com.example.demo.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {

    CustomerService customerService;

    @GetMapping("ranking/bySpentMoney")
    public ResponseEntity<List<CustomerDto>> getRankingByMoney() {
        return ResponseEntity.ok(customerService.rankingByMoney());
    }

    @GetMapping("ranking/byWatchedMovies")
    public ResponseEntity<List<CustomerDto>> getRankingByMovies() {
        return ResponseEntity.ok(customerService.rankingByMovies());
    }

    @GetMapping("activity/rentMoviesByMonth/{year}")
    public ResponseEntity<List<RentalDto>> getRentsByMonth(@PathVariable int year) {
        return ResponseEntity.ok(customerService.rentRanking(year, 0));
    }

    @GetMapping("activity/rentMoviesByMonth/{year}/{id}")
    public ResponseEntity<List<RentalDto>> getRentsByMonthAndCustomer(@PathVariable int year, @PathVariable int id) {
        return ResponseEntity.ok(customerService.rentRanking(year, id));
    }

}
