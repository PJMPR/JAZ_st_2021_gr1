package com.example.demo.business.handlers;

import com.example.demo.business.CategoryService;
import com.example.demo.web.DTOs.FilmDTO;
import java.util.List;

public class CategoryHandler extends Handler {
    CategoryService categoryService;

    public CategoryHandler(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean check(FilmDTO filmDTO) {
        if(filmDTO.getFilmCategories() != null){
            List<String> categories = categoryService.getAllCategories();
            for (String category: filmDTO.getFilmCategories()) {
                if(categories.stream().noneMatch(s -> s.equalsIgnoreCase(category))){
                    categoryService.addCategory(category);
                }
            }
        }
        return checkNext(filmDTO);
    }
}
