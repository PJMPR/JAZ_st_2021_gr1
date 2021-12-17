package com.lab08.dbapp.repositories;

import com.lab08.dbapp.model.Film;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {

}
