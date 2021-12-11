package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDto {

    int id;
    @JsonProperty(value = "original_title", access = JsonProperty.Access.WRITE_ONLY)
    String title;
    String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
