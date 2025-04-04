package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;

public interface CategoryService {
    int addCategory(CategoryDTO categoryDTO);
    ResponseDTO getAllCategories();
    ResponseDTO getAllCategoryIds();
    ResponseDTO getCategoryIdByName(String categoryName);
    boolean hasAssociatedServices(int categoryId);
    ResponseDTO getCategoryById(int categoryId);
    int updateCategory(CategoryDTO categoryDTO);
    ResponseDTO deleteCategory(int categoryId);
}