package com.example.demo.business;

import com.example.demo.data.model.Category;
import com.example.demo.data.model.Language;
import com.example.demo.data.repositories.CategoryRepository;
import com.example.demo.data.repositories.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategories(){
        return categoryRepository.findAllDistinctCategories();
    }

    public void addCategory(String category) {
        categoryRepository.save(new Category(category, new Timestamp(new Date().getTime())));
    }
}
