package com.example.demo.business;

import com.example.demo.data.model.Film;
import com.example.demo.data.model.Language;
import com.example.demo.data.projections.FilmProjection;
import com.example.demo.data.repositories.FilmRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FilmService {
    private final LanguageService languageService;
    private final FilmRepository filmRepository;

    public FilmService(LanguageService languageService, FilmRepository filmRepository) {
        this.languageService = languageService;
        this.filmRepository = filmRepository;
    }

    public List<FilmProjection> getFilmsOnPage(Integer page, Film film) {
        film.setLanguage(languageService.getLanguageByName(film.getLanguage().getLanguageId()));
        return filmRepository.findAllFilms(PageRequest.of(page , 10), film).getContent();
    }

    public Integer saveFilm(Film film) {
        film.setLanguage(languageService.getLanguageByName(film.getLanguage().getLanguageId()));
        film.setLastUpdate(new Timestamp(new Date().getTime()));
        return filmRepository.save(film).getFilmId();
    }

    public Integer updateFilm(Integer id, Film film) {
        film.setLanguage(languageService.getLanguageByName(film.getLanguage().getLanguageId()));
        Film update = filmRepository.findById(id).orElse(null);
        if(update != null) {
            update.setTitle(film.getTitle());
            update.setReleaseYear(film.getReleaseYear());
            update.setRentalDuration(film.getRentalDuration());
            update.setRentalRate(film.getRentalRate());
            update.setReplacementCost(film.getReplacementCost());
            update.setLanguage(film.getLanguage());
        }

        return filmRepository.save(film).getFilmId();
    }

    public Integer deleteFilm(Integer id) {
        filmRepository.deleteById(id);
        return id;
    }
}
