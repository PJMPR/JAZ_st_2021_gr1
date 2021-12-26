package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public void updateFilm(int id, FilmDto updateFilm) {
        Calendar timestamp = Calendar.getInstance();
        entityManager.joinTransaction();
        entityManager.createQuery("UPDATE Film SET " +
                        "title = ?1, " +
                        "release_year = ?2, " +
                        "rental_duration = ?3, " +
                        "rental_rate = ?4, " +
                        "replacement_cost = ?5, " +
                        "last_update = ?6, " +
                        "language_id = ?7 " +
                        "where id=" + id)


                .setParameter(1, updateFilm.getTitle())
                .setParameter(2, updateFilm.getReleaseYear())
                .setParameter(3, updateFilm.getRentalDuration())
                .setParameter(4, updateFilm.getRentalRate())
                .setParameter(5, updateFilm.getReplacementCosts())
                .setParameter(6, Timestamp.from(timestamp.getTime().toInstant()))
                .setParameter(7, updateFilm.getLanguage().getId())
                .executeUpdate();


    }

    public void createFilm(FilmDto newFilm) {
        Calendar timestamp = Calendar.getInstance();
        entityManager.joinTransaction();
        entityManager.createNativeQuery("INSERT INTO Film" +
                        "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                        "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, newFilm.getTitle())
                .setParameter(2, newFilm.getReleaseYear())
                .setParameter(3, newFilm.getRentalDuration())
                .setParameter(4, newFilm.getRentalRate())
                .setParameter(5, newFilm.getReplacementCosts())
                .setParameter(6, Timestamp.from(timestamp.getTime().toInstant()))
                .setParameter(7, newFilm.getLanguage().getId()).executeUpdate();
    }


    public List<Film> getFilms() {
        return entityManager.createQuery("SELECT film FROM Film film", Film.class)
                .setMaxResults(20)
                .setFirstResult(1000)
                .getResultList();

    }


    public void deleteFilmById(int id) {
        entityManager.joinTransaction();
        entityManager.createQuery("" + "DELETE FROM Film WHERE filmId=" + id).executeUpdate();
    }
}
