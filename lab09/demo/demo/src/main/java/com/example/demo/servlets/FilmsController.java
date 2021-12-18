package com.example.demo.servlets;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmService filmService;

    private static final List<FilmDto> films = List.of(
            new FilmDto(1, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(2, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(3, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(4, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(5, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(6, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(7, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(8, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(9, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish"))


    ).stream().collect(Collectors.toList());

    @GetMapping
    public ResponseEntity getFilms(){
        return ResponseEntity
                .ok(filmService.getFilms());
    }

}
