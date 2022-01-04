package com.example.demo.controllers;

import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.Services.ChartsType;
import com.example.demo.Services.CustomerService;
import com.example.demo.Services.FieldType;
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

    @GetMapping
    @RequestMapping("ranking/bySpentMoney.jpg/{chart}")
    public ResponseEntity getByMoneyChart(@PathVariable("chart") String chart) throws IOException {
        if(chart.equals("pie")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.customerChart(
                            ChartsType.PIE,
                            FieldType.MONEY,
                            "Customers by money spent",
                            "",
                            "",
                            customerService.rankingByMoney()));

        }else if (chart.equals("bar")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.customerChart(
                            ChartsType.BAR,
                            FieldType.MONEY,
                            "Customers by money spent",
                            "",
                            "",
                            customerService.rankingByMoney()));
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @RequestMapping("ranking/byWatchedMovies.jpg/{chart}")
    public ResponseEntity getByMoviesChart(@PathVariable("chart") String chart) throws IOException {
        if(chart.equals("bar")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.customerChart(
                            ChartsType.BAR,
                            FieldType.MOVIES,
                            "Customers by movies watched",
                            "",
                            "",
                            customerService.rankingByMovies()));
        }else if(chart.equals("pie")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.customerChart(
                            ChartsType.PIE,
                            FieldType.MOVIES,
                            "Customers by movies watched",
                            "",
                            "",
                            customerService.rankingByMovies()));
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @RequestMapping("activity/rentMoviesByMonth.jpg/{year}/{chart}")
    public ResponseEntity getRentMoviesByMonthChart(@PathVariable("year") int year, @PathVariable("chart") String chart) throws IOException {
        if(chart.equals("bar")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.rentalChart(
                            ChartsType.BAR,
                            "Movies rental by month",
                            "months",
                            "rentals",
                            customerService.rentRanking(year, 0)
                    ));
        }else if(chart.equals("pie")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(customerService.rentalChart(
                            ChartsType.PIE,
                            "Movies rental by month ",
                            "",
                            "",
                            customerService.rentRanking(year, 0)));
        }
        return ResponseEntity.ok(customerService.rentRanking(year, 0));
    }
}
