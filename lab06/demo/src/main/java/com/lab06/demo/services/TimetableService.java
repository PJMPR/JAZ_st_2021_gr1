package com.lab06.demo.services;

import com.lab06.demo.creditcalculators.CreditConstant;
import com.lab06.demo.creditcalculators.CreditDecreasing;
import com.lab06.demo.models.Calculation;
import com.lab06.demo.models.Timetable;
import com.lab06.demo.repositories.TimetableRepository;
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
            case CONSTANT: {
                timetableRepository.saveAll(new CreditConstant(calculation).constantRateCalculation());
                break;
            }
            case DECREASING: { 
                timetableRepository.saveAll(new CreditDecreasing(calculation).decreasingRateCalculation());
                break;
            }
        }
    }

    public List<Timetable> getTimetable(long id) {
        return timetableRepository.findAllById(id);
    }
}
