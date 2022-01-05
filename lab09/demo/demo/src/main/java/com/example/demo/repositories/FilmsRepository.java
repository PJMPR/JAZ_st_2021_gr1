package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    private FilmDto createFilmDto(Film film){
        return FilmDto.builder()
                .id(film.getFilmId())
                .title(film.getTitle())
                .releaseYear(film.getReleaseYear())
                .language(LanguageDto.builder()
                        .id(film.getLanguage().getLanguageId())
                        .name(film.getLanguage().getName())
                        .build())
                .rentalDuration(new BigDecimal(film.getRentalDuration()))
                .rentalRate(film.getRentalRate())
                .replacementCosts(film.getReplacementCost())
                .build();
    }

    public List<FilmDto> getFilms(int page, int size) {
        return (List<FilmDto>) entityManager.createNativeQuery("SELECT * from Film limit "+size+" offset "+(page-1) * size, Film.class)
                .getResultStream()
                .map(film -> createFilmDto((Film) film))
                .collect(Collectors.toList());
    }

}
