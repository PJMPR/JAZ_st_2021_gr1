package com.example.demo.contracts;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDto {
    private Integer id;
    private String title;
    private Integer releaseYear;
    private BigDecimal rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
    private LanguageDto language;

}
