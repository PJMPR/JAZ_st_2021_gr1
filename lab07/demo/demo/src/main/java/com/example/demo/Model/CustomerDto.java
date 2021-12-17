package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private int customerID;
    private String name;
    private String surname;
    private BigDecimal moneySpent;
    private int watchedMovies;
}
