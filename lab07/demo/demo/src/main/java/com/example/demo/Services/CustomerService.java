package com.example.demo.Services;

import com.example.demo.Model.CustomerDto;
import com.example.demo.Model.RentalDto;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerRepository customerRepository;

    private List<CustomerDto> getStats() {
        return customerRepository.findAll().stream()
                .map(customer -> CustomerDto.builder()
                        .customerID(customer.getCustomerId())
                        .name(customer.getFirstName())
                        .surname(customer.getLastName())
                        .moneySpent(customer.moneySpent())
                        .watchedMovies(customer.moviesWatched())
                        .build()
                ).collect(Collectors.toList());
    }

    public List<CustomerDto> rankingByMoney() {
        return getStats().stream()
                .sorted(Comparator.comparing(CustomerDto::getMoneySpent).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<CustomerDto> rankingByMovies() {
        return getStats().stream()
                .sorted(Comparator.comparing(CustomerDto::getWatchedMovies).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }


    public List<RentalDto> rentRanking(int year, int id) {
        ArrayList<RentalDto> rentInMonth = new ArrayList<>();
        ArrayList<Integer> allRents = new ArrayList<>();

        if(id == 0)
        {
            IntStream.rangeClosed(1, 12)
                    .forEach(i -> allRents
                            .add(customerRepository.findAll()
                                    .stream()
                                    .map(customer -> customer.moviesInMonth(year, i))
                                    .reduce(0, Integer::sum)));
        }
        else
        {
            IntStream.rangeClosed(1, 12)
                    .forEach(i -> allRents
                            .add(customerRepository.findById(id)
                                    .stream()
                                    .map(customer -> customer.moviesInMonth(year, i))
                                    .reduce(0, Integer::sum)));
        }

        IntStream.rangeClosed(1, 12)
                .forEach(i -> rentInMonth
                        .add(new RentalDto(i, allRents.get(i - 1))));

        return rentInMonth;
    }

}
