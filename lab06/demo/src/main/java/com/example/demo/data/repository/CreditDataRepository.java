package com.example.demo.data.repository;

import com.example.demo.data.entity.CreditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditDataRepository extends JpaRepository<CreditData, Long> {

    @NonNull
    Optional<CreditData> findById(@NonNull Long id);

    @NonNull
    List<CreditData> findAll();
}
