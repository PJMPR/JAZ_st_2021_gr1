package Service;

import Loan.Installment;
import Loan.InstallmentCalculator;
import Loan.TimeTable;
import Repository.TimeTabelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeTableService {
    TimeTabelRepo timeTabelRepo;

    @Autowired
    public TimeTableService(TimeTabelRepo timeTabelRepo){
        this.timeTabelRepo = timeTabelRepo;
    }

    public void addTimeTable(TimeTable timeTable){
        timeTabelRepo.save(timeTable);
    }

    public TimeTable findTimeTable(long id){
        return timeTabelRepo.findById(id);
    }

    public List<Installment> calculateInstallment(TimeTable timeTable){
        InstallmentCalculator installmentCalculator = new InstallmentCalculator();
        return installmentCalculator.calculateInstallmnet(timeTable);
    }
}
