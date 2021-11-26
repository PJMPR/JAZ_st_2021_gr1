package com.example.demo.web;


import com.example.demo.business.CreditDataService;
import com.example.demo.business.TimetableService;
import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit/timetable")
public class TimetableRestController {

    private final TimetableService timetableService;

    @Autowired
    public TimetableRestController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/{id}")
    public List<Timetable> getTimetableById(@PathVariable Long id){
        return timetableService.getTimetable(id);
    }
}
