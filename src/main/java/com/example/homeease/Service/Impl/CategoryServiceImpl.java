package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Category;
import com.example.homeease.Repo.CategoryRepository;
import com.example.homeease.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setImage(category.getImage());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}