package com.example.demo.repositories;

import com.example.demo.Exception.FilmNotFoundException;
import com.example.demo.Exception.NoSuchPageException;
import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;
    private final Calendar calendar = Calendar.getInstance();

    private FilmDto createFilmDto(Film film){
        return FilmDto.builder()
                .id(film.getFilmId())
                .title(film.getTitle())
                .releaseYear(film.getReleaseYear())
                .language(LanguageDto.builder()
                        .id(film.getLanguage().getLanguageId())
                        .name(film.getLanguage().getName())
                        .build())
                .rentalDuration(new BigDecimal(film.getRentalDuration()))
                .rentalRate(film.getRentalRate())
                .replacementCosts(film.getReplacementCost())
                .build();
    }

    private String appendToQuery(FilmDto film){
        String str = "";
        if(film.getId() != null ){
            str+="film_id="+film.getId().toString()+" and ";
        }
        if(film.getTitle() != null && !Objects.equals(film.getTitle(), "")){
            str+="title='"+film.getTitle()+ "' and ";
        }
        if(film.getReleaseYear() != null){
            str+="release_year="+film.getReleaseYear().toString()+" and ";
        }
        if(film.getRentalDuration() != null){
            str+="rental_duration="+film.getRentalDuration().toString()+" and ";
        }
        if(film.getRentalRate() != null){
            str+="rental_rate="+film.getRentalRate().toString()+" and ";
        }
        if(film.getReplacementCosts() != null){
            str+="replacement_cost="+film.getReplacementCosts().toString()+" and ";
        }
        if(film.getLanguage().getId() != null && film.getLanguage().getId() !=7){
            str+="language_id="+film.getLanguage().getId().toString()+" and ";
        }

        if(!str.equals("")){
            return "where "+str+"true";
        }else return str;
    }

    public List<FilmDto> getFilms(int page, int size, FilmDto filmDto) {
        String query="SELECT * FROM film "+appendToQuery(filmDto);
        try {
            return (List<FilmDto>) entityManager.createNativeQuery(query+" limit "+size+" offset "+(page-1)*size, Film.class).getResultStream()
                    .map(film -> createFilmDto((Film) film)).collect(Collectors.toList());
        }catch (Exception e){
            throw new NoSuchPageException();
        }
    }

    @Transactional
    public HttpStatus createFilm(FilmDto film){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("INSERT INTO Film " +
                            "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                            "VALUES (?,?,?,?,?,?,?)")
                    .setParameter(1, film.getTitle())
                    .setParameter(2, film.getReleaseYear())
                    .setParameter(3, film.getRentalDuration())
                    .setParameter(4, film.getRentalRate())
                    .setParameter(5, film.getReplacementCosts())
                    .setParameter(6, Timestamp.from(calendar.getTime().toInstant()))
                    .setParameter(7, film.getLanguage().getId())
                    .executeUpdate();
            return HttpStatus.CREATED;
        }catch (Exception e) {
            return HttpStatus.LOCKED;
        }
    }

    @Transactional
    public HttpStatus deleteFilm(Integer id){
        try {
            entityManager.joinTransaction();
            entityManager.createQuery("delete from Film f where f.filmId=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            throw new FilmNotFoundException();
        }
    }

    @Transactional
    public HttpStatus updateFilm(FilmDto film){
        try {
            entityManager.joinTransaction();
            entityManager.createQuery("UPDATE Film f set " +
                    "f.title=:title," +
                    "f.releaseYear=:releaseYear," +
                    "f.rentalDuration=:rentalDuration," +
                    "f.replacementCost=:replacementCost," +
                    "f.lastUpdate=:lastUpdate," +
                    "f.rentalRate=:rentalRate," +
                    "f.language.languageId=:languageId " +
                            "Where f.filmId=:id")
                    .setParameter("title", film.getTitle())
                    .setParameter("releaseYear", film.getReleaseYear())
                    .setParameter("rentalDuration", film.getRentalDuration().intValue())
                    .setParameter("replacementCost", film.getReplacementCosts())
                    .setParameter("lastUpdate", Timestamp.from(calendar.getTime().toInstant()))
                    .setParameter("rentalRate", film.getRentalRate())
                    .setParameter("languageId", film.getLanguage().getId())
                    .setParameter("id", film.getId())
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e) {
            //throw new FilmNotFoundException();
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }
}
