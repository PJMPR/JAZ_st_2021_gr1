package com.example.demo.business;

import com.example.demo.business.calculations.Calculator;
import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.Timetable;
import com.example.demo.data.repository.TimetableRepository;
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

    public void calculateCreditData(CreditData creditData) {
        timetableRepository.saveAll(Calculator.calculate(creditData));
    }

    public List<Timetable> getTimetable(Long id) {
        return timetableRepository.getByCreditDataId(id);
    }

}
