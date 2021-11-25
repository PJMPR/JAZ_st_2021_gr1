package pl.pjwstk.lab06.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import pl.pjwstk.lab06.object.Credit;
import pl.pjwstk.lab06.object.RepaymentSchedule;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RepaymentScheduleService {

    private RepaymentSchedule repaymentSchedule;
    private Firestore db;

    public RepaymentScheduleService(){
        this.repaymentSchedule = new RepaymentSchedule();
        this.db = FirestoreClient.getFirestore();
    }
    public String createRepaymentSchedule(Credit credit){

        repaymentSchedule.setInterest(calculateInterest(repaymentSchedule.getAmmount(),repaymentSchedule.));
        repaymentSchedule.setCapital(calculateCapital(repaymentSchedule.getStarting(),1));
        repaymentSchedule.setAmmount(calculateAmount(repaymentSchedule.getCapital(),repaymentSchedule.getInterest()));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(this.repaymentSchedule);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private float calculateInterest(float capitalToPay,float percentage,int yearAmount){
        return (capitalToPay*percentage)/yearAmount;
    }
    private float calculateCapital(float start, int number){
        return start/number;
    }
    private float calculateAmount(float capital, float interest){
        return capital + interest;
    }

}
