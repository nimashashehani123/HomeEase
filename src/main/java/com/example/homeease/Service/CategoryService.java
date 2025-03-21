package com.example.homeease.Service;

import com.example.homeease.Entity.Category;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category updateCategory(int id, Category category);
    void deleteCategory(int id);
}