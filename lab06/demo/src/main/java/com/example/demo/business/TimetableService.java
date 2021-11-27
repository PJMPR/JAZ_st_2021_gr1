package com.example.demo.business;

import com.example.demo.business.calculations.Calculator;
import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.Files;
import com.example.demo.data.entity.Timetable;
import com.example.demo.data.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public void calculateCreditData(CreditData creditData) {
        timetableRepository.saveAll(Objects.requireNonNull(Calculator.calculate(creditData)));
    }

    public List<Timetable> getTimetable(Long id) {
        return timetableRepository.getByCreditDataId(id);
    }

    public byte[] getTimeTableToFile(Long id, Files file) {
        List<Timetable> timetables = this.getTimetable(id);
        switch (file){
            case CSV: return CSVCreator.toFile(timetables);
            case PDF: return PDFCreator.toFile(timetables);
        }
        return null;
    }
}
