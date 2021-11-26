package com.example.demo.business;

import com.example.demo.data.entity.CreditData;
import com.example.demo.data.repository.CreditDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditDataService {
    private final TimetableService timetableService;
    private final CreditDataRepository creditDataRepository;

    @Autowired
    public CreditDataService(TimetableService timetableService, CreditDataRepository creditDataRepository) {
        this.timetableService = timetableService;
        this.creditDataRepository = creditDataRepository;
    }

    public void saveCreditData(CreditData creditData) {
        creditDataRepository.save(creditData);
        timetableService.calculateCreditData(creditData);
    }

    public List<CreditData> getAllCreditData() {
        return creditDataRepository.findAll();
    }
}
