package com.example.demo.business.calculations;

import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.Timetable;

import java.util.List;

public interface Calculation {
    boolean canCalculate(CreditData creditData);
    List<Timetable> calculate(CreditData creditData);
}
