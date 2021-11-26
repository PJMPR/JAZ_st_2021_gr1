package com.lab06.demo.creditcalculators;

import java.util.ArrayList;
import java.util.List;

import com.lab06.demo.models.Timetable;

public abstract class Credit {
    List<Timetable> timetableList = new ArrayList<>();
    int amount;
    int installmentCount;
    double percentage;
    double fixedRate;
    double capitalStillToPay;
    double capitalAlreadyPaid;

    public Credit(int amount, int installmentCount, double percentage, double fixedRate) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }
}
