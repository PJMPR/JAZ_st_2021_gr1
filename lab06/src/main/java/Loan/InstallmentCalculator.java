package Loan;

import java.util.ArrayList;
import java.util.List;

public class InstallmentCalculator {

    private double calculateInterest(TimeTable timeTable, int number) {
        double interest;
        if(timeTable.getTypeOfInstallment().equals("CONSTANT") ){
            interest = timeTable.getFixedFee() * timeTable.getProcentage();
        }else if(timeTable.getTypeOfInstallment().equals("DECREASING")){
            interest = timeTable.getFixedFee() * (timeTable.getInstallmentCount() - number + 1) * timeTable.getProcentage();
        }else interest = 0;

        return interest;
    }

    public List<Installment> calculateInstallmnet(TimeTable timeTable){
        double amount;
        double interest;
        double capital;
        double capitalToPay;

        List<Installment> installments = new ArrayList<>();

        for(int number = 1; number <= timeTable.getInstallmentCount();number++){

            interest = calculateInterest(timeTable,number);
            amount = timeTable.getFixedFee() + interest;
            capitalToPay = timeTable.getAmount() - timeTable.getFixedFee() * number;
            capital = (timeTable.getAmount() - capitalToPay) / timeTable.getAmount();
            Installment installment = new Installment(timeTable.getId(), number, Math.round(amount), Math.round(interest), timeTable.fixedFee, capitalToPay, Math.round(capital));
            installments.add(installment);
        }
        return installments;
    }
}
