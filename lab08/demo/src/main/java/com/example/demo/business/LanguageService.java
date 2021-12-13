package com.example.demo.business;

import com.example.demo.data.model.Language;
import com.example.demo.data.repositories.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class LanguageService {
    LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<String> getAllLanguages(){
        return languageRepository.findAllDistinctLanguages();
    }

    public void addLanguage(String language) {
        languageRepository.save(new Language(language, new Timestamp(new Date().getTime())));
    }

    public Language getLanguage(String language) {
        return languageRepository.findByName(language);
    }
}
