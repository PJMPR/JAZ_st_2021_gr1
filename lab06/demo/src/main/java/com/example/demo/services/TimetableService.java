package com.example.demo.services;

import com.example.demo.creditcalculators.CreditConstant;
import com.example.demo.creditcalculators.CreditDecreasing;
import com.example.demo.entities.Calculation;
import com.example.demo.entities.Timetable;
import com.example.demo.repositories.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public void addNewTimetable(Calculation calculation) {
        switch (calculation.getInstallmentType()){
            case constant -> timetableRepository.saveAll(new CreditConstant(calculation).constantRateCalculation());
            case decreasing -> timetableRepository.saveAll(new CreditDecreasing(calculation).decreasingRateCalculation());
        }
    }

    public List<Timetable> getTimetable(long id) {
        return timetableRepository.findAllById(id);
    }
}
