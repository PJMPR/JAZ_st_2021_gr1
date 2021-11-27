package pl.pjwstk.lab06.object;

import org.springframework.stereotype.Component;

@Component
public class Credit {
    private long id;
    private int amount;
    private int installmentCount;
    private String installmentType;
    private float percentage;
    private int fixedFee;

    public Credit(){};
    public Credit(long id,int amount, int installmentCount, String installmentType, int percentage, int fixedFee) {
        this.id = id;
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage/100;
        this.fixedFee = fixedFee;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public void setInstallmenType(String installmenType) {
        this.installmentType = installmenType;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage/100;
    }

    public void setFixedFee(int fixedFee) {
        this.fixedFee = fixedFee;
    }

    public long getId(){return this.id;}

    public int getAmount() {
        return amount;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public String getInstallmentType() {
        return installmentType;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getFixedFee() {
        return fixedFee;
    }
    @Override
    public String toString(){
        return String.format("{'ammount':'%d','installmentCount':'%d','installmentType':'%s,'percentage':'%f','fixedFee':'%d'}",getAmount(),getInstallmentCount(),getInstallmentType(),getPercentage(),getFixedFee());
    }
}
