package com.example.demo.contracts;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDto {
    private int id;
    private String title;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
    private LanguageDto language;

}
