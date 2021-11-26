package controller;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Calculator {
    public ArrayList<Installment> calculateInstalments(Timetable timetable) {
        ArrayList<Installment> installments = new ArrayList<>();
        double capital = 0;

        if (timetable.getInstallmentType() == InstallmentType.constant) {

            constantCalculations(timetable, installments, capital);

        } else if (timetable.getInstallmentType() == InstallmentType.decreasing) {

            decreasingCalculations(timetable, installments, capital);

        }

        return installments;
    }

    private void decreasingCalculations(Timetable timetable, ArrayList<Installment> installments, double capital) {
        double capitalToPay = timetable.getAmount();
        double percentage = timetable.getPercentage();
        long count = timetable.getInstallmentCount();
        double installment = timetable.getAmount() * percentage * Math.pow((percentage + 1), count) / (Math.pow((percentage + 1), count) - 1);
        double amount;

        for (int i = 0; i < timetable.getInstallmentCount(); i++) {
            double interest = capitalToPay * percentage;
            amount = installment + interest;
            installments.add(new Installment(timetable.getId(), i + 1, capital, interest, timetable.getFixedFee(), capitalToPay, amount));
            capital += amount;
            capitalToPay -= amount;
        }
    }

    private void constantCalculations(Timetable timetable, ArrayList<Installment> installments, double capital) {
        double totalInterest = (timetable.getPercentage() * timetable.getAmount()) + (timetable.getFixedFee() * timetable.getInstallmentCount());
        double monthlyInterest = totalInterest / timetable.getInstallmentCount();
        double capitalToPay = totalInterest + timetable.getAmount();
        double installment = capitalToPay / timetable.getInstallmentCount();

        for (int i = 0; i < timetable.getInstallmentCount(); i++) {
            installments.add(new Installment(timetable.getId(), i + 1, capital, monthlyInterest, timetable.getFixedFee(), capitalToPay, installment));
            capital += installment;
            capitalToPay -= installment;
        }
    }
}
