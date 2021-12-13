package com.example.demo.business;

import com.example.demo.business.handlers.ActorsHandler;
import com.example.demo.business.handlers.CategoryHandler;
import com.example.demo.business.handlers.Handler;
import com.example.demo.business.handlers.LanguageHandler;
import com.example.demo.data.model.Film;
import com.example.demo.data.model.Language;
import com.example.demo.data.repositories.FilmRepository;
import com.example.demo.web.DTOs.FilmDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@AllArgsConstructor
public class FilmService {
    @Autowired
    private ModelMapper modelMapper;

    CategoryService categoryService;
    LanguageService languageService;
    ActorService actorService;
    FilmRepository filmRepository;

    public void updateFilm(FilmDTO filmDTO){
        Handler handler = new LanguageHandler(languageService);
        handler.linkWith(new CategoryHandler(categoryService))
                .linkWith(new ActorsHandler(actorService));
        handler.check(filmDTO);

        Film film = modelMapper.map(filmDTO, Film.class);
        film.setLanguage(languageService.getLanguage(filmDTO.getLanguage().get(0)));
        film.setLastUpdate(new Timestamp(new Date().getTime()));
        filmRepository.save(film);

        Status status = Status.getInstance();
        status.setProgress(status.getProgress() + 1);
    }


}

