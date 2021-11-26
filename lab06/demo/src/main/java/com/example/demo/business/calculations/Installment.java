package com.example.demo.business.calculations;


import com.example.demo.data.entity.CreditData;
import org.springframework.stereotype.Component;

@Component
public class Installment {
    private CreditData creditData;
    private double baseAmountToPay;
    private double capitalAlreadyPaid;
    private double capitalStillToPay;
    private double interest;
    private double monthlyRate;

    public Installment() {}

    public Installment(CreditData creditData) {
        this.creditData = creditData;
        this.baseAmountToPay = creditData.getAmount() / creditData.getInstallmentCount();
    }

    public void calculateCapitalAlreadyPaid(int installment_number){
        capitalAlreadyPaid = baseAmountToPay * (installment_number + 1);
    }

    public void calculateCapitalStillToPay(){
        capitalStillToPay = creditData.getAmount() - capitalAlreadyPaid;
    }

    public CreditData getCreditData() {
        return creditData;
    }

    public double getBaseAmountToPay() {
        return baseAmountToPay;
    }

    public double getCapitalAlreadyPaid() {
        return capitalAlreadyPaid;
    }

    public double getCapitalStillToPay() {
        return capitalStillToPay;
    }

    public double getInterest() {
        return interest;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }
}