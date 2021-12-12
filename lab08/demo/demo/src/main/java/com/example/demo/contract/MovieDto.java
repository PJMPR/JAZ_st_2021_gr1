package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDto {

    int id;
    @JsonProperty(value = "original_title", access = JsonProperty.Access.WRITE_ONLY)
    String title;
    String overview;
    List<GenreDto> genres;

}
