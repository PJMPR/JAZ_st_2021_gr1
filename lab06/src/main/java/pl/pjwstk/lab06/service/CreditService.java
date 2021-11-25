package pl.pjwstk.lab06.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;
import pl.pjwstk.lab06.object.Credit;
import pl.pjwstk.lab06.object.RepaymentSchedule;

import java.util.concurrent.ExecutionException;

public class CreditService {

    private Firestore db;


    public CreditService(){
        this.db = FirestoreClient.getFirestore();
    }

    public String createCredit(Credit credit) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = db.collection("Credit").document(String.valueOf(credit.getId())).set(credit);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Credit getCredit(String creditId) throws ExecutionException, InterruptedException {
        DocumentReference dc = db.collection("Credit").document(creditId);
        ApiFuture<DocumentSnapshot> future = dc.get();
        DocumentSnapshot document = future.get();

        Credit credit;
        if (document.exists()){
            credit = document.toObject(Credit.class);
            return credit;
        }
        return null;
    }
    public String updateCredit(String creditId){
        return "Successfully updated";
    }
    public String deleteCredit(String creditId){
        ApiFuture<WriteResult> writeResult = db.collection("Credit").document(creditId).delete();
        return "Successfully deleted " + creditId;
    }
}
