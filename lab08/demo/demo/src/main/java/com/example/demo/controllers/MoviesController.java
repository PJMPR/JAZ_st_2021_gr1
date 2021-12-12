package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import com.example.demo.service.TheMovieDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

@Controller
@RequestMapping("movies-client")
@RequiredArgsConstructor
public class MoviesController {
    TheMovieDBService theMovieDBService;


    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getMovie(@PathVariable("id") int id){
        var movie = theMovieDBService.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/updater/status")
    public ResponseEntity getSystemStatus(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(theMovieDBService.getSystemStatusInfo());
    }

    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(theMovieDBService.reloadData());
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year){
        return ResponseEntity.ok(theMovieDBService.reloadDataByYear(year));
    }
}
