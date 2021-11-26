package com.example.lab06.service;

import com.example.lab06.cretitcalculators.CreditConstant;
import com.example.lab06.cretitcalculators.CreditDecreasing;
import com.example.lab06.func.Calculation;
import com.example.lab06.func.Timetable;
import com.example.lab06.repositories.TimetableRepository;
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
            case CONSTANT -> timetableRepository.saveAll(new CreditConstant(calculation).constantRateCalculation());
            case DECREASING -> timetableRepository.saveAll(new CreditDecreasing(calculation).decreasingRateCalculation());
        }
    }

    public List<Timetable> getTimetable(long id) {
        return timetableRepository.findAllById(id);
    }
}
