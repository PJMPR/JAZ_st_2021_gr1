package com.example.demo.servlets;

import com.example.demo.contracts.LanguageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/languages")
public class LanguageController {

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getLanguages() {
        return ResponseEntity.ok(List.of(
                new LanguageDto(1, "Polish"),
                new LanguageDto(2, "English"),
                new LanguageDto(3, "French")
        ));
    }

}
