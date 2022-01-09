package com.example.demo.data.repositories;

import com.example.demo.data.model.Language;
import com.example.demo.data.projections.LanguageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query("SELECT DISTINCT l FROM Language l")
    List<LanguageProjection> findAllLanguages();

    Language findLanguageByLanguageId(Integer languageId);
}
