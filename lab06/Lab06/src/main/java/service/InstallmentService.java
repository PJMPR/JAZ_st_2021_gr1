package service;

import controller.Calculator;
import controller.Installment;
import controller.Timetable;
import repository.InstallmentRepository;

import java.util.List;

public class InstallmentService {
    InstallmentRepository installmentRepository;
    Calculator installmentCalculator;

    public InstallmentService(InstallmentRepository installmentRepository, Calculator installmentCalculator) {
        this.installmentRepository = installmentRepository;
        this.installmentCalculator = installmentCalculator;
    }

    public List<Installment> calculateInstallments(Timetable timetable) {
        return installmentCalculator.calculateInstalments(timetable);
    }

    public void saveInstallments(Installment installment) {
        installmentRepository.save(installment);
    }
}
