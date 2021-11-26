package com.example.lab06.repositories;

import com.example.lab06.func.Calculation;
import org.springframework.data.repository.CrudRepository;

public interface CalculationRepository extends CrudRepository<Calculation, Long> {
    Calculation findById(long id);
}
