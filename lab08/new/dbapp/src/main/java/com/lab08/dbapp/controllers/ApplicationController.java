package com.lab08.dbapp.controllers;

import com.lab08.dbapp.services.TheMovieDBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    public TheMovieDBService movieDBService;

    @GetMapping("/updater/reload")
    public ResponseEntity reloadEntity(@RequestParam(required = false, defaultValue = "1980") Integer year) {
        movieDBService.getMoviesIdsByReleaseDate(year);
        return null;
    }

}
