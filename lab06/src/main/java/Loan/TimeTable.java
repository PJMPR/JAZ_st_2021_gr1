package Loan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public double procentage;
    public double fixedFee;
    public String typeOfInstallment;
    public int installmentCount;
    public double amount;

    @OneToMany(         cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="timetable_id")
    private List<Installment> installments = new ArrayList<>();

    public TimeTable(){}

    public TimeTable(LoanParameters loanParameters) {
        this.amount = loanParameters.amount();
        this.installmentCount = loanParameters.installmentCount();
        this.procentage = loanParameters.percentage();
        this.fixedFee = loanParameters.fixedFee();
        this.typeOfInstallment = loanParameters.installmentType();
        this.installments = new ArrayList<>();
    }

    public TimeTable(long id, double procentage, double fixedFee, String typeOfInstallment, int installmentCount, double amount) {
        this.id = id;
        this.procentage = procentage;
        this.fixedFee = fixedFee;
        this.typeOfInstallment = typeOfInstallment;
        this.installmentCount = installmentCount;
        this.amount = amount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getProcentage() {
        return procentage;
    }

    public void setProcentage(double procentage) {
        this.procentage = procentage;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }

    public String getTypeOfInstallment() {
        return typeOfInstallment;
    }

    public void setTypeOfInstallment(String typeOfInstallment) {
        this.typeOfInstallment = typeOfInstallment;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}
