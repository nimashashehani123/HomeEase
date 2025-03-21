package com.example.homeease.Service.Impl;
import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Entity.Category;
import com.example.homeease.Repo.CategoryRepository;
import com.example.homeease.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setImage(categoryDTO.getImage());
        Category savedCategory = categoryRepository.save(category);
        return convertToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setImage(categoryDTO.getImage());
        Category updatedCategory = categoryRepository.save(category);
        return convertToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return convertToCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setImage(category.getImage());
        return categoryDTO;
    }
}