package pl.pjwstk.lab06.controller;

import org.springframework.web.bind.annotation.*;
import pl.pjwstk.lab06.object.Credit;
import pl.pjwstk.lab06.service.CreditService;
import pl.pjwstk.lab06.object.RepaymentSchedule;
import pl.pjwstk.lab06.service.RepaymentScheduleService;

import java.util.concurrent.ExecutionException;


@RestController
public class CreditController {

    public CreditService creditService;
    public RepaymentScheduleService repaymentScheduleService;

    public CreditController(CreditService creditService,RepaymentScheduleService repaymentScheduleService){
        this.repaymentScheduleService = repaymentScheduleService;
        this.creditService = creditService;
    }
    @PostMapping(value = "/credit/calculations",consumes = "application/json",produces = "application/json")
    public String createCredit(@RequestBody Credit credit)throws InterruptedException, ExecutionException{
        return repaymentScheduleService.createRepaymentSchedule(credit);
    }
    @GetMapping("/credit/timetable/")
    public String getRepaymentSchedule(@RequestParam String creditId) throws ExecutionException, InterruptedException {
                Credit credit = creditService.getCredit(creditId);
        return repaymentScheduleService.createRepaymentSchedule(credit).toString();
    }
    @GetMapping("/get")
    public String getCredit(@RequestParam String creditId) throws ExecutionException, InterruptedException {
        return  creditService.getCredit(creditId).toString();
    }
    @GetMapping("/all")
    public String getAll(){
        return "All";
    }
}
