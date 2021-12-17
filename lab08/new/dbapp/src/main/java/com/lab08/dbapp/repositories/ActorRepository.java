package com.lab08.dbapp.repositories;

import com.lab08.dbapp.model.Actor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {

}
