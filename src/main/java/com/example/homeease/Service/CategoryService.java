package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;

public interface CategoryService {
    ResponseDTO addCategory(CategoryDTO categoryDTO);
    ResponseDTO getAllCategories();
    ResponseDTO getCategoryById(int categoryId);
    ResponseDTO updateCategory(int categoryId, CategoryDTO categoryDTO);
    ResponseDTO deleteCategory(int categoryId);
}