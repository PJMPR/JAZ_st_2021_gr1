package com.example.demo.business.handlers;

import com.example.demo.business.ActorService;
import com.example.demo.web.DTOs.FilmDTO;


public class ActorsHandler extends Handler{
    ActorService actorService;

    public ActorsHandler(ActorService actorService) {
        this.actorService = actorService;
    }

    @Override
    public boolean check(FilmDTO filmDTO) {
        if(filmDTO.getFilmActors() != null){
            for (String actor: filmDTO.getFilmActors()) {
                String[] names = actor.split(" ");
                if(actorService.checkIfExist(names[0], names[1])){
                    actorService.addActor(names[0], names[1]);
                }
            }
        }
        return checkNext(filmDTO);
    }
}
