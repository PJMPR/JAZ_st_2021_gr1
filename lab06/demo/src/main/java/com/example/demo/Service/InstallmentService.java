package com.example.demo.Service;

import com.example.demo.Calculator.Calculator;
import com.example.demo.PKO.Installment;
import com.example.demo.PKO.Timetable;
import com.example.demo.Repo.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstallmentService {
    InstallmentRepository installmentRepository;
    Calculator calculator;

    @Autowired
    public InstallmentService(InstallmentRepository installmentRepository, Calculator calculator) {
        this.installmentRepository = installmentRepository;
        this.calculator = calculator;
    }

    public void calculateInstallment(Timetable timetable){
        calculator.calculateInstalments(timetable).forEach(this::saveInstallments);
    }

    public void saveInstallments(Installment installment){
        installmentRepository.save(installment);
    }
}
