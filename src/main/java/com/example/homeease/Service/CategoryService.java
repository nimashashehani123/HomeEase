package com.example.homeease.Service;
import com.example.homeease.Dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(int categoryId);
    CategoryDTO getCategoryById(int categoryId);
    List<CategoryDTO> getAllCategories();
}
