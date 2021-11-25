package pl.pjwstk.lab06.object;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Component
public class RepaymentSchedule {
    Map<String,String> schedule;
    int number;
    float capital;
    float fixedFee;
    float interest;
    float capitalToPay;
    float ammount;
    float starting;
    double percentage;

    public RepaymentSchedule(){
        int number = 0;
        int capital = 0;
    };

    public float getStarting() {
        return starting;
    }

    public void setStarting(float starting) {
        this.starting = starting;
    }

    public void setSchedule(String key, String val){
        schedule.put(key,val);
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }

    public float getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(float fixedFee) {
        this.fixedFee = fixedFee;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getCapitalToPay() {
        return capitalToPay;
    }

    public void setCapitalToPay(float capitalToPay) {
        this.capitalToPay = capitalToPay;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }
}
