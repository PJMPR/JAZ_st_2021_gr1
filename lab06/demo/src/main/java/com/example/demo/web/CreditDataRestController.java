package com.example.demo.web;


import com.example.demo.business.CreditDataService;
import com.example.demo.data.entity.CreditData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditDataRestController {

    private final CreditDataService creditDataService;

    @Autowired
    public CreditDataRestController(CreditDataService creditDataService) {
        this.creditDataService = creditDataService;
    }

    @PostMapping("/calculations")
    public ResponseEntity<Long> createCreditData(@RequestBody CreditData creditData){
        creditDataService.saveCreditData(creditData);
        return new ResponseEntity<>(creditData.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/calculations")
    public ResponseEntity<List<CreditData>> getAllCreditData(){
        return new ResponseEntity<>(creditDataService.getAllCreditData(), HttpStatus.OK);
    }
}
