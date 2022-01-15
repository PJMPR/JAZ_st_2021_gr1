package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHendler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({FilmNotFoundException.class})
    public ResponseEntity handleFilmNotFoundException(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({NoSuchPageException.class})
    public ResponseEntity handleNoSuchPageException(){
        return ResponseEntity.notFound().build();
    }
}
