package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("movies-client")
public class MoviesController {

    RestTemplate rest;

    @Value("${themoviedb.api.key}")
    String apiKey;

    public MoviesController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping("{id}")
    public ResponseEntity getMovie(@PathVariable int id){
        var movie =  rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + apiKey, MovieDto.class)
                .getBody();
        return ResponseEntity.ok(movie);

    }
}
