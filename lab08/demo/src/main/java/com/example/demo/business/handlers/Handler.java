package com.example.demo.business.handlers;

import com.example.demo.web.DTOs.FilmDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public abstract class Handler {
    private Handler next;

    public Handler linkWith(Handler next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(FilmDTO filmDTO);

    protected boolean checkNext(FilmDTO filmDTO) {
        return next == null || next.check(filmDTO);
    }
}
