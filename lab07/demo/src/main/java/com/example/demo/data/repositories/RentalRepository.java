package com.example.demo.data.repositories;

import com.example.demo.data.entities.Payment;
import com.example.demo.data.projections.Rental.incomeFromTo;
import com.example.demo.data.projections.Rental.incomeByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Payment, Integer> {
    @Query("SELECT MONTH(p.paymentDate) as month, SUM(p.amount) as income " +
        "FROM Payment as p " +
        "WHERE YEAR(p.paymentDate) = YEAR(:timestamp)" +
        "GROUP BY month ORDER BY month")
    List<incomeByMonth> getIncomeByMonth(Timestamp timestamp);


    @Query("SELECT CAST(DATE_FORMAT(date(p.paymentDate), '%Y-%m-01') as date) as month, SUM(p.amount) as income " +
            "FROM Payment as p " +
            "WHERE p.paymentDate BETWEEN :from AND :to " +
            "GROUP BY month ORDER BY month")
    List<incomeFromTo> getIncomeFromTo(Timestamp from, Timestamp to);
}