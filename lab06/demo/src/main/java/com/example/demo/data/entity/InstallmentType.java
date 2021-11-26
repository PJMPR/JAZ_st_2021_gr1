package com.example.demo.data.entity;

import com.example.demo.business.calculations.Installment;
import com.example.demo.data.repository.TimetableRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public enum InstallmentType {
    DECREASING{
        @Override
        public List<Timetable>  execute(Installment installment) {
            List<Timetable> timetables = new ArrayList<>();
            CreditData creditData = installment.getCreditData();
            for (int i = 0; i < creditData.getInstallmentCount(); i++) {
                installment.calculateCapitalAlreadyPaid(i);
                installment.calculateCapitalStillToPay();
                calculateMonthlyRate(installment);
                calculateInterest(installment);

                timetables.add(new Timetable(
                        i + 1,
                        installment.getCapitalAlreadyPaid(),
                        installment.getInterest(),
                        creditData.getFixedRate(),
                        installment.getCapitalStillToPay(),
                        installment.getMonthlyRate(),
                        creditData
                ));
            }
            return timetables;
        }

        @Override
        void calculateMonthlyRate(Installment installment) {
            double capitalStillToPay = installment.getCapitalStillToPay();
            double percentage = installment.getCreditData().getPercentage();
            double fixedRate = installment.getCreditData().getFixedRate();
            installment.setMonthlyRate(setPosition((capitalStillToPay * percentage)/12 + fixedRate,2));
        }

        @Override
        void calculateInterest(Installment installment) {
            installment.setInterest(setPosition(installment.getMonthlyRate() - 30,2));
        }
    },

    CONSTANT{
        @Override
        public List<Timetable> execute(Installment installment) {
            List<Timetable> timetables = new ArrayList<>();
            CreditData creditData = installment.getCreditData();
            calculateMonthlyRate(installment);
            calculateInterest(installment);

            for (int i = 0; i < creditData.getInstallmentCount(); i++) {
                installment.calculateCapitalAlreadyPaid(i);
                installment.calculateCapitalStillToPay();

                timetables.add(new Timetable(
                        i + 1,
                        installment.getCapitalAlreadyPaid(),
                        installment.getInterest(),
                        creditData.getFixedRate(),
                        installment.getCapitalStillToPay(),
                        installment.getMonthlyRate(),
                        creditData
                ));
            }
            return timetables;
        }

        @Override
        void calculateMonthlyRate(Installment installment) {
            CreditData creditData = installment.getCreditData();
            double q = 1 + (creditData.getPercentage()/12);

            installment.setMonthlyRate(
                    setPosition(
                            (creditData.getAmount()*Math.pow(q,creditData.getInstallmentCount())*(q-1))
                                    /(Math.pow(q,creditData.getInstallmentCount())-1) + creditData.getFixedRate(),
                            2
                    )
            );
        }

        @Override
        void calculateInterest(Installment installment) {
            installment.setInterest(
                    setPosition((installment.getMonthlyRate() - installment.getBaseAmountToPay() - 30), 2)
            );
        }
    };

    public abstract List<Timetable> execute(Installment installment);
    abstract void calculateMonthlyRate(Installment installment);
    abstract void calculateInterest(Installment installment);

    double setPosition(Double number, Integer position){
        return new BigDecimal(number).setScale(position, RoundingMode.HALF_UP).doubleValue();
    }
}
