package com.example.demo.contract;

import com.example.demo.model.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class MovieDto {

    private String title;
    private int id;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("overview")
    private String overview;
    private int year;
    private String rating;

    private List<Actor> actors;

    private String imdb_id;

    public MovieDto(String title, int id, int runtime, String overview, int year, String rating, List<Actor> actors, String imdb_id) {
        this.title = title;
        this.id = id;
        this.runtime = runtime;
        this.overview = overview;
        this.year = year;
        this.rating = rating;
        this.actors = actors;
        this.imdb_id = imdb_id;
    }


}
