package com.example.demo.servlets;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import com.example.demo.repositories.FilmsRepository;
import com.example.demo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmService filmService;

    private static final List<FilmDto> films = Stream.of(
            new FilmDto(1, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(2, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(3, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(4, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(5, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(6, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(7, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(8, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
            new FilmDto(9, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish"))


    ).collect(Collectors.toList());

    private final FilmsRepository filmsRepository;


    //Get Film
    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        List<Film> films = filmsRepository.getFilms();
        return ResponseEntity.ok(films);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity deleteFilm(@PathVariable int id) {
        filmsRepository.deleteFilmById(id);
        return ResponseEntity.noContent().build();
    }

    //Create
    @PostMapping
    public ResponseEntity createFilm(@RequestBody FilmDto newFilm) {
        filmsRepository.createFilm(newFilm);
        return ResponseEntity.ok().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity updateFilm(@PathVariable int id, @RequestBody FilmDto updateFilm) {
        filmsRepository.updateFilm(id, updateFilm);
        return ResponseEntity.ok().build();
    }

}
