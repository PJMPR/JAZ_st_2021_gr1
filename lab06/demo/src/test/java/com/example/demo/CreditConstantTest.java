package com.example.demo;

import com.example.demo.creditcalculators.CreditConstant;
import com.example.demo.entities.Calculation;
import com.example.demo.entities.InstallmentType;
import com.example.demo.entities.Timetable;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditConstantTest {
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
}
