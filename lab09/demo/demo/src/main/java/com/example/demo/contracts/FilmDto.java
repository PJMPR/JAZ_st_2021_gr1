package com.example.demo.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Integer id;
    private String title;
    private Integer releaseYear;
    private LanguageDto language;
    private BigDecimal rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
}
=======
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {
    private int id;
    private String title;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
    private LanguageDto language;

}
/*
export interface Film{
    id:number,
    title:string,
    releaseYear?:number,
    language?:Language,
    rentalDuration?:number,
    rentalRate?:number,
    replacementCosts?:number
}
* */
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
