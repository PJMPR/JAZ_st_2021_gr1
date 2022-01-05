package com.example.demo.servlets;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.repositories.FilmsRepository;
import com.example.demo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmsRepository filmsRepository;

    @GetMapping
    public ResponseEntity<List<FilmDto>> filmsList(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(required = false) Integer languageId){
        return ResponseEntity.ok(filmsRepository.getFilms(page, 10));
    }

}
