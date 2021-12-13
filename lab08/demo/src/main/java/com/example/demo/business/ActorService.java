package com.example.demo.business;

import com.example.demo.data.model.Actor;
import com.example.demo.data.model.Category;
import com.example.demo.data.repositories.ActorRepository;
import com.example.demo.data.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ActorService {
    ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public boolean checkIfExist(String firstName, String lastName){
        Actor actor = actorRepository.findByFirstNameAndLastName(firstName, lastName);
        return actor == null;
    }

    public void addActor(String firstName, String lastName) {
        actorRepository.save(new Actor(firstName, lastName, new Timestamp(new Date().getTime())));
    }
}
