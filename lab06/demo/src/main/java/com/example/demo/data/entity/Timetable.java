package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Timetable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double number;
    private double capital;
    private double interest;
    private double fixedFee;
    private double capitalToPay;
    private double amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credit_data_id", nullable = false)
    private CreditData creditData;

    public Timetable() {}

    public Timetable(double number, double capital, double interest, double fixedFee, double capitalToPay, double amount, CreditData creditData) {
        this.number = number;
        this.capital = capital;
        this.interest = interest;
        this.fixedFee = fixedFee;
        this.capitalToPay = capitalToPay;
        this.amount = amount;
        this.creditData = creditData;
    }

    public CreditData getCreditData() {
        return creditData;
    }

    public void setCreditData(CreditData creditData) {
        this.creditData = creditData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }

    public double getCapitalToPay() {
        return capitalToPay;
    }

    public void setCapitalToPay(double capitalToPay) {
        this.capitalToPay = capitalToPay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
