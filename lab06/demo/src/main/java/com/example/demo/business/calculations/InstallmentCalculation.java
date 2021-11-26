package com.example.demo.business.calculations;

import com.example.demo.data.entity.CreditData;
import com.example.demo.data.entity.InstallmentType;
import com.example.demo.data.entity.Timetable;

import java.util.List;

public class InstallmentCalculation implements Calculation{
    private final InstallmentType installmentType;

    public InstallmentCalculation(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }

    @Override
    public boolean canCalculate(CreditData creditData) {
        return creditData.getInstallmentType().equals(installmentType);
    }

    @Override
    public List<Timetable> calculate(CreditData creditData) {
       return installmentType.execute(new Installment(creditData));
    }
}

