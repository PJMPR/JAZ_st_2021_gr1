package com.example.demo.Service;

import com.example.demo.PKO.Installment;
import com.example.demo.Service.InstallmentService;
import com.example.demo.PKO.Timetable;
import com.example.demo.Repo.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimetableService {
    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public int saveTimetable(Timetable timetable){
        timetableRepository.save(timetable);
        return timetable.getId();
    }

    public Timetable getTimetable(int id){
        return timetableRepository.findById(id);
    }

}
