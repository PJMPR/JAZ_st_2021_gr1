package com.example.demo.services;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmsRepository filmsRepository;

    public List<FilmDto> getFilms(){
        return filmsRepository.getFilms()
                .stream()
                .map(x->new FilmDto(x.getFilmId(), x.getTitle(), x.getReleaseYear(), x.getRentalDuration(), x.getRentalRate(), x.getReplacementCost(),
                        new LanguageDto(x.getLanguage().getLanguageId(), x.getLanguage().getName())))
                .collect(Collectors.toList());
    }
}
