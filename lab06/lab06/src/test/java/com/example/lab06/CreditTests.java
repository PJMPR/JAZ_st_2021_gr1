package com.example.demo;

import com.example.lab06.cretitcalculators.CreditConstant;
import com.example.lab06.cretitcalculators.CreditDecreasing;
import com.example.lab06.func.Calculation;
import com.example.lab06.func.InstallmentType;
import com.example.lab06.func.Timetable;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditTests {
    @Test
    public void creditConstantShouldReturnProperValuesInList() {
        int amount = 500000;
        int installmentCount = 2000;
        InstallmentType installmentType = InstallmentType.constant;
        double percentage = 0.037;
        int fixedRate = 50;

        Calculation calculation = new Calculation(amount, installmentCount, installmentType, percentage, fixedRate);
        List<Timetable> actual = new CreditConstant(calculation).constantRateCalculation();

        assertThat(actual.size(), is(2000));
        assertThat(actual.get(420).getInterest(), is(1313.29));
        assertThat(actual.get(1337).getAmount(), is(1593.29));
    }

    @Test
    public void creditDecreasingShouldReturnProperValuesInList() {
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