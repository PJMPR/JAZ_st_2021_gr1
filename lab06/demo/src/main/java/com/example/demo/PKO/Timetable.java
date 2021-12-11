package com.example.demo.PKO;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long amount; //wnioskowana kwota kredytu
    private long installmentCount; //ilosc rat
    private installmentType installmentType; // rodzaj rat
    private double percentage;
    private long fixedFee;
    @OneToMany
    @JoinColumn(name = "timetable_id")
    private List<Installment> instalments = new ArrayList<>();

    public Timetable() {
    }

    public Timetable(long amount, long installmentCount, installmentType installmentType, double percentage, long fixedFee) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedFee = fixedFee;
        this.instalments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(long installmentCount) {
        this.installmentCount = installmentCount;
    }

    public installmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(installmentType installmentType)
    {
        this.installmentType = installmentType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public long getFixedFee() {
        return fixedFee;
    }

    public List<Installment> getInstalments() {
        return instalments;
    }
}