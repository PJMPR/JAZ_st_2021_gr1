package com.example.demo.business.handlers;

import com.example.demo.business.LanguageService;
import com.example.demo.web.DTOs.FilmDTO;

import java.util.List;

public class LanguageHandler extends Handler {
    LanguageService languageService;

    public LanguageHandler(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public boolean check(FilmDTO filmDTO) {
        if(filmDTO.getLanguage() != null){
            List<String> languages = languageService.getAllLanguages();
            for (String language: filmDTO.getLanguage()) {
                if(languages.stream().noneMatch(s -> s.equalsIgnoreCase(language))){
                    languageService.addLanguage(language);
                }
            }
        }
        return checkNext(filmDTO);
    }
}
