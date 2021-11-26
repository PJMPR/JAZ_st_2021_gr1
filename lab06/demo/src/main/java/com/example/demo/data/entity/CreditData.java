package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class CreditData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    private Long installmentCount;

    @Enumerated(EnumType.STRING)
    private InstallmentType installmentType;

    private double percentage;

    private Long fixedRate;

    @OneToMany(mappedBy="creditData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Timetable> timetables;


    public CreditData(){
        fixedRate = 0L;
        percentage = 0L;
        installmentType = InstallmentType.CONSTANT;
        installmentCount = 0L;
        timetables = null;
    }

    public CreditData(Long id, Long amount, Long installmentCount, InstallmentType installmentType, double percentage, Long fixedRate) {
        this.id = id;
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Long installmentCount) {
        this.installmentCount = installmentCount;
    }

    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Long getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(Long fixedRate) {
        this.fixedRate = fixedRate;
    }

    public Set<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(Set<Timetable> timetables) {
        this.timetables = timetables;
    }
}
