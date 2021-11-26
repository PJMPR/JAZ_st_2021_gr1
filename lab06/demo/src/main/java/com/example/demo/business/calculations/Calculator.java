package com.example.demo.business.calculations;

import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.InstallmentType;
import com.example.demo.data.entity.Timetable;
import com.example.demo.data.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculator {
    private static List<InstallmentCalculation> calculations = List.of(
                new InstallmentCalculation(InstallmentType.CONSTANT),
                new InstallmentCalculation(InstallmentType.DECREASING)
    );

    public static InstallmentCalculation findInstallmentCalculation (CreditData creditData){
        return calculations.stream()
                .filter( v -> v.canCalculate(creditData))
                .findAny().orElse(null);
    }

    public static List<Timetable> calculate(CreditData creditData){
        InstallmentCalculation installmentCalculation = findInstallmentCalculation(creditData);
        if(installmentCalculation != null)
            return installmentCalculation.calculate(creditData);
        else return null;
    }
}
