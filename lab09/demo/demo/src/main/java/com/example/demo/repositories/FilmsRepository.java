package com.example.demo.repositories;

import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217

import javax.persistence.EntityManager;
import java.util.List;

@Repository
<<<<<<< HEAD
=======
@Transactional
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

<<<<<<< HEAD
    public List<Film> getFilmsByPage(int page, int size) {

        var films = entityManager.createQuery("" +
                        "SELECT film FROM Film film", Film.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        return films;
    }
}
=======
    public List<Film> getFilms(){
       return entityManager.createQuery("SELECT film FROM Film film", Film.class)
                .setMaxResults(20)
                .setFirstResult(30)
                .getResultList();

    }
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
