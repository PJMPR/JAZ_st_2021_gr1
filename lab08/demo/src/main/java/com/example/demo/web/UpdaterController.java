package com.example.demo.web;

import com.example.demo.business.ActorService;
import com.example.demo.business.LanguageService;
import com.example.demo.business.Status;
import com.example.demo.business.UpdaterService;
import com.example.demo.data.repositories.ActorRepository;
import com.example.demo.data.repositories.LanguageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("updater")
@AllArgsConstructor
public class UpdaterController {
    UpdaterService updaterService;
    ActorService actorService;

    @GetMapping(value = "/reload")
    @ResponseBody
    public ResponseEntity<?> getUpdate(@RequestParam(defaultValue = "1980") String year){
        Status.getInstance().setStartStatus();
        updaterService.startUpdate(Integer.parseInt(year));
        Status.getInstance().setEndStatus();
        return ResponseEntity.ok(Status.getInstance());
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(){
        return ResponseEntity.ok(Status.getInstance());
    }
}
