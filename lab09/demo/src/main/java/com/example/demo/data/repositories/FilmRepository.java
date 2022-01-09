package com.example.demo.data.repositories;

import com.example.demo.data.model.Film;
import com.example.demo.data.projections.FilmProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query("SELECT f FROM Film f WHERE " +
            "(:#{#film.filmId} IS NULL OR f.filmId = :#{#film.filmId}) AND " +
            "(:#{#film.title} IS NULL OR f.title LIKE :#{#film.title}) AND " +
            "(:#{#film.releaseYear} IS NULL OR f.releaseYear = :#{#film.releaseYear}) AND " +
            "(:#{#film.rentalDuration} IS NULL OR f.rentalDuration = :#{#film.rentalDuration}) AND " +
            "(:#{#film.rentalRate} IS NULL OR f.rentalRate = :#{#film.rentalRate}) AND " +
            "(:#{#film.replacementCost} IS NULL OR f.replacementCost = :#{#film.replacementCost}) AND" +
            "(:#{#film.language} IS NULL OR f.language = :#{#film.language})")
    Page<FilmProjection> findAllFilms(Pageable pageable, @Param("film") Film film);
}