package com.example.demo.servlets;

import com.example.demo.contracts.FilmDto;
import com.example.demo.repositories.FilmsRepository;
import com.example.demo.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmService filmService;
    private final FilmsRepository filmsRepository;

//    private static final List<FilmDto> films = List.of(
//            new FilmDto(1, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(2, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(3, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(4, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(5, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(6, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(7, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(8, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish")),
//            new FilmDto(9, "tytul", 2001, 2, new BigDecimal(2.99), new BigDecimal(20.99), new LanguageDto(1, "polish"))
//
//
//    ).stream().collect(Collectors.toList());

    @GetMapping
    public ResponseEntity getFilms() {
        return ResponseEntity
                .ok(filmService.getFilms());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFilm(@PathVariable int id) {
        filmsRepository.deleteFilmById(id);
        return ResponseEntity.ok(filmService.getFilms());
    }

    @PostMapping
    public ResponseEntity createFilm(@RequestBody FilmDto newFilm) {
        filmsRepository.createFilm(newFilm);
        return ResponseEntity.ok(filmService.getFilms());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFilm(@PathVariable int id, @RequestBody FilmDto updateFilm) {
        filmsRepository.updateFilm(id, updateFilm);
        return ResponseEntity.ok(filmService.getFilms());
    }

}
