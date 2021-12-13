package com.example.demo.data.repositories;

import com.example.demo.data.model.Language;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query("SELECT DISTINCT l.name FROM Language l")
    List<String> findAllDistinctLanguages();

    Language findByName(String name);
}