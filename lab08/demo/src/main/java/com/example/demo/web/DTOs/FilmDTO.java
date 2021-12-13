package com.example.demo.web.DTOs;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Getter
@Setter
@Data
public class FilmDTO {
    private String title;
    private String description;
    private int length;
    private int releaseYear;
    private String rating;
    private List<String> language;
    private List<String> filmActors;
    private List<String> filmCategories;
}
