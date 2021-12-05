package com.example.demo.business.services;

import com.example.demo.business.parsers.StringToTimestamp;
import com.example.demo.data.entities.Payment;
import com.example.demo.data.projections.Customer.CustomerBySpentMoney;
import com.example.demo.data.projections.Customer.CustomerByWatchedMovies;
import com.example.demo.data.projections.Customer.RentMoviesByMonth;
import com.example.demo.data.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<BigDecimal> getCustomerPayments(int id){
//    Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        return repository.getById(id)
                .getPayments()
                .stream()
                .map(Payment::getAmount)
                .collect(Collectors.toList());
    }


    public List<CustomerBySpentMoney> getCustomersBySpentMoneyWithLimit(int limit) {
        return repository.getCustomersBySpentMoney().stream().limit(limit).toList();
    }

    public List<CustomerByWatchedMovies> getCustomersByWatchedMoviesWithLimit(int limit) {
        return repository.getCustomersByWatchedMovies().stream().limit(limit).toList();
    }

    public List<RentMoviesByMonth> getRentMoviesByMonth(String year) {
        return repository.getRentMoviesByMonth(StringToTimestamp.parse(year));
    }

    public List<RentMoviesByMonth> getCustomerRentMoviesByMonth(Integer customerId) {
        return repository.getCustomerRentMoviesByMonth(customerId);
    }

}

