package com.example.demo.controllers;

import com.example.demo.service.FilmService;
import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.contract.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.util.Calendar;

@Controller
@RequestMapping("movies-client")
public class MoviesController {

    @Value("${themoviedb.api.key}")
    String apiKey;

    RestTemplate rest;
    FilmService filmService;

    @GetMapping("{id}")
    public ResponseEntity getMovie(@PathVariable int id){
        var movie =  rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + apiKey, MovieDto.class)
                .getBody();
        return ResponseEntity.ok(movie);

    }
    @GetMapping
    @RequestMapping("/imdb/{id}")
    public ResponseEntity<IMDBMovieDto> getDataIMDB(@PathVariable("id") String id){
        return ResponseEntity.ok(filmService.getMovieInfoFromIMDB(id));
    }

    @PostMapping("/updater/status")
    public ResponseEntity getSystemStatus(){
        return ResponseEntity.ok(filmService.getSystemStatusInfo());
    }

    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(filmService.reloadData());
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year) {
        return ResponseEntity.ok(filmService.reloadDataByYear(year));
    }
}
