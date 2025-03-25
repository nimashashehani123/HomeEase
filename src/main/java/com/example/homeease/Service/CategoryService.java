package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;

public interface CategoryService {
    int addCategory(CategoryDTO categoryDTO);
    ResponseDTO getAllCategories();
    ResponseDTO getAllCategoryIds();
    ResponseDTO getCategoryIdByName(String categoryName);

    ResponseDTO getCategoryById(int categoryId);
    ResponseDTO updateCategory(int categoryId, CategoryDTO categoryDTO);
    ResponseDTO deleteCategory(int categoryId);
}