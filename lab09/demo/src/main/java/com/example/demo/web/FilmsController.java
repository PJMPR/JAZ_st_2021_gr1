package com.example.demo.web;

import com.example.demo.business.LanguageService;
import com.example.demo.data.model.Film;
import com.example.demo.business.FilmService;
import com.example.demo.data.model.Language;
import com.example.demo.web.contracts.LanguageDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.contracts.FilmDTO;

import java.math.BigDecimal;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {
    private final FilmService filmService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getFilms(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer release_year,
            @RequestParam(required = false) Integer rental_duration,
            @RequestParam(required = false) BigDecimal rental_rate,
            @RequestParam(required = false) BigDecimal replacement_costs,
            @RequestParam(required = false) Integer language
    ){
        FilmDTO filmDTO = FilmDTO.builder()
                .filmId(id)
                .title(title)
                .releaseYear(release_year)
                .rentalDuration(rental_duration)
                .rentalRate(rental_rate)
                .replacementCost(replacement_costs)
                .language(LanguageDTO.builder().languageId(language).name("").build()).build();

        return ResponseEntity.ok(filmService.getFilmsOnPage(page - 1, modelMapper.map(filmDTO,Film.class)));
    }

    @PostMapping
    public ResponseEntity<?> saveFilm(@RequestBody FilmDTO filmDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmService.saveFilm(modelMapper.map(filmDTO, Film.class)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Integer id, @RequestBody Film film){
        return ResponseEntity.ok(filmService.updateFilm(id, film));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id){
        return ResponseEntity.ok(filmService.deleteFilm(id));
    }
}
