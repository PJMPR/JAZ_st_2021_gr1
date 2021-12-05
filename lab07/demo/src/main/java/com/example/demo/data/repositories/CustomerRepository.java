package com.example.demo.data.repositories;

import com.example.demo.data.entities.Customer;
import com.example.demo.data.projections.Customer.CustomerBySpentMoney;
import com.example.demo.data.projections.Customer.CustomerByWatchedMovies;
import com.example.demo.data.projections.Customer.RentMoviesByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c as customer, SUM(p.amount) as spent " +
            "FROM Customer c JOIN Payment p ON c.customerId = p.customerByCustomerId " +
            "GROUP BY c.customerId ORDER BY spent DESC")
    List<CustomerBySpentMoney> getCustomersBySpentMoney();

    @Query("SELECT c as customer, COUNT(r.rentalId) as watched " +
            "FROM Customer c JOIN Rental r ON c.customerId = r.customerByCustomerId " +
            "WHERE r.returnDate is not null " +
            "GROUP BY c.customerId ORDER BY watched DESC")
    List<CustomerByWatchedMovies> getCustomersByWatchedMovies();

    @Query("SELECT MONTH(r.rentalDate) as month, COUNT(r.rentalId) as rentMovies " +
            "FROM Rental as r " +
            "WHERE YEAR(r.rentalDate) = YEAR(:timestamp)" +
            "GROUP BY month ORDER BY month")
    List<RentMoviesByMonth> getRentMoviesByMonth(Timestamp timestamp);

    @Query("SELECT MONTH(r.rentalDate) as month, COUNT(r.rentalId) as rentMovies " +
            "FROM Rental as r " +
            "WHERE r.customerByCustomerId.customerId = :customerId " +
            "GROUP BY month ORDER BY month")
    List<RentMoviesByMonth> getCustomerRentMoviesByMonth(Integer customerId);
}
