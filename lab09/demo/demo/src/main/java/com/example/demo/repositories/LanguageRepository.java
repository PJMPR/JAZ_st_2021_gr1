package com.example.demo.repositories;

import com.example.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    @Query("SELECT language FROM Language language WHERE language.name=:name")
    Language findByName(String name);
}
