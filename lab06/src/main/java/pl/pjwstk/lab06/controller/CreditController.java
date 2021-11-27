package pl.pjwstk.lab06.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.api.client.util.IOUtils;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.lab06.object.Credit;
import pl.pjwstk.lab06.service.CreditService;
import pl.pjwstk.lab06.object.RepaymentSchedule;
import pl.pjwstk.lab06.service.RepaymentScheduleService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
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
    public Long createCredit(@RequestBody Credit credit)throws InterruptedException, ExecutionException{
        creditService.createCredit(credit);
        return repaymentScheduleService.createRepaymentSchedule(credit);
    }
    @GetMapping("/credit/timetable/{id}")
    public String getRepaymentSchedule(@PathVariable String id) throws ExecutionException, InterruptedException, JsonProcessingException {

        return repaymentScheduleService.getRepaymentSchedule(id);
    }


    @GetMapping("/get")
    public String getCredit(@RequestParam String creditId) throws ExecutionException, InterruptedException {
        return  creditService.getCredit(creditId).toString();
    }
}
