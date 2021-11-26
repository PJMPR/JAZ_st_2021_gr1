package com.example.demo;

import com.example.demo.creditcalculators.CreditDecreasing;
import com.example.demo.entities.Calculation;
import com.example.demo.entities.InstallmentType;
import com.example.demo.entities.Timetable;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditDecreasingTest {
    @Test
    public void creditConstantShouldReturnProperValuesInList() {
        int amount = 500000;
        int installmentCount = 2000;
        InstallmentType installmentType = InstallmentType.decreasing;
        double percentage = 0.037;
        int fixedRate = 50;

        Calculation calculation = new Calculation(amount, installmentCount, installmentType, percentage, fixedRate);
        List<Timetable> actual = new CreditDecreasing(calculation).decreasingRateCalculation();

        assertThat(actual.size(), is(2000));
        assertThat(actual.get(251).getInterest(), is(1368.19));
        assertThat(actual.get(502).getInterest(), is(1174.71));
        assertThat(actual.get(753).getAmount(), is(1011.23));
        assertThat(actual.get(1004).getAmount(), is(817.75));
    }
}
