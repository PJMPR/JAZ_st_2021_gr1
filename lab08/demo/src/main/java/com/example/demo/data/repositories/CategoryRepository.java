package com.example.demo.data.repositories;

import com.example.demo.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT DISTINCT c.name FROM Category c")
    List<String> findAllDistinctCategories();
}