package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int id) throws ResourceNotFoundException;
    Category updateCategory(int id, Category category) throws ResourceNotFoundException;
    void deleteCategory(int id) throws ResourceNotFoundException;
}