package com.example.demo.repositories;

import com.example.demo.entities.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Long> {
    Calculation findById(long id);
}
