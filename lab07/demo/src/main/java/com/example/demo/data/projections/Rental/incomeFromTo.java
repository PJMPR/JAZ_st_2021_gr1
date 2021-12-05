package com.example.demo.data.projections.Rental;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.Date;

public interface incomeFromTo {
    Date getMonth();
    BigDecimal getIncome();
}
