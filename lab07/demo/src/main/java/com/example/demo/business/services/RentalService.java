package com.example.demo.business.services;

import com.example.demo.business.converters.StringToTimestampConverter;
import com.example.demo.data.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository repository;

    @Autowired
    public RentalService(RentalRepository repository) {
        this.repository = repository;
    }

    public List<?> getIncomeByMonth(String year) {
        return repository.getIncomeByMonth(new StringToTimestampConverter().convert(year));
    }

    public List<?> getIncomeFromTo(Date from, Date to) {
        return repository.getIncomeFromTo(new Timestamp(from.getTime()), new Timestamp(to.getTime()));
    }
}
