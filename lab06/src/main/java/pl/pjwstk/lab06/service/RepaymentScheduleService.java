package pl.pjwstk.lab06.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import pl.pjwstk.lab06.object.Credit;
import pl.pjwstk.lab06.object.RepaymentSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RepaymentScheduleService {

    private RepaymentSchedule repaymentSchedule;
    private Firestore db;

    public RepaymentScheduleService(){
        this.repaymentSchedule = new RepaymentSchedule();
        this.db = FirestoreClient.getFirestore();
    }
    public Long createRepaymentSchedule(Credit credit){

        float pay = calculateAmount(credit.getPercentage(),credit.getFixedFee(),credit.getAmount(),credit.getInstallmentCount());

        for (int i = 1; i <= credit.getInstallmentCount(); i++) {
            repaymentSchedule.setSchedule(String.valueOf(i),String.valueOf(pay));
        }
        ApiFuture<WriteResult> collectionsApiFuture = db.collection("RepaymentSchedule").document(String.valueOf(credit.getId())).set(repaymentSchedule.getSchedule());

        return credit.getId();
    }
    public String getRepaymentSchedule(String id) throws ExecutionException, InterruptedException, JsonProcessingException {
        String json = getScheduleJSON(id);
        return json;
    }

    private String getScheduleJSON(String  id) throws ExecutionException, InterruptedException, JsonProcessingException {
        DocumentReference dc = db.collection("RepaymentSchedule").document(id);
        ApiFuture<DocumentSnapshot> future = dc.get();
        DocumentSnapshot document = future.get();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(document.getData());
        return json;
    }
    private float calcluatePercentage(float percentage ){
        return (percentage/100)+1;
    }
    private float calculateAmount(float percentage, float fixedFee,float amount,int installmentCount){
        float result = (amount*calcluatePercentage(percentage)) + ((fixedFee*installmentCount)*calcluatePercentage(percentage));
        result = result/installmentCount;
        return result;
    }

}
