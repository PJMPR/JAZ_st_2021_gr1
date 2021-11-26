package Controller;

import Loan.Installment;
import Loan.LoanParameters;
import Loan.TimeTable;
import Service.InstallmentService;
import Service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {
    InstallmentService installmentService;
    TimeTableService timeTableService;

    @Autowired
    public LoanController(InstallmentService installmentService, TimeTableService timeTableService){
        this.installmentService = installmentService;
        this.timeTableService = timeTableService;
    }

    @GetMapping("credit/timetable")
    public TimeTable getTimeTable(@RequestBody Long id){
        return timeTableService.findTimeTable(id);
    }

    @PostMapping("/credit/calculations")
    public Long postInstallmentData (@RequestBody LoanParameters loanParameters){
        TimeTable timeTable = new TimeTable(loanParameters);

        timeTableService.addTimeTable(timeTable);
        List<Installment> installments = timeTableService.calculateInstallment(timeTable);
        installments.forEach(installment -> installmentService.addInstallment(installment));

        return timeTable.getId();
    }
}
