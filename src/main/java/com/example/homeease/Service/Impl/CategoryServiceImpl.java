package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Entity.Category;
import com.example.homeease.Repo.CategoryRepository;
import com.example.homeease.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO addCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsById(categoryDTO.getCategoryId())) {
            return new ResponseDTO(400, "Category already exists with id: " + categoryDTO.getCategoryId(), null);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);
        return new ResponseDTO(200, "Category added successfully", categoryDTO);
    }

    @Override
    public ResponseDTO getAllCategories() {
        List<CategoryDTO> categoryList = modelMapper.map(categoryRepository.findAll(),
                new TypeToken<List<CategoryDTO>>() {}.getType());
        return new ResponseDTO(200, "Categories retrieved successfully", categoryList);
    }

    @Override
    public ResponseDTO getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return new ResponseDTO(200, "Category retrieved successfully", categoryDTO);
    }

    @Override
    public ResponseDTO updateCategory(int categoryId, CategoryDTO categoryDTO) {
        if (!categoryRepository.existsById(categoryId)) {
            return new ResponseDTO(404, "Category not found with id: " + categoryId, null);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryId(categoryId); // Ensure the ID is preserved
        categoryRepository.save(category);
        return new ResponseDTO(200, "Category updated successfully", categoryDTO);
    }

    @Override
    public ResponseDTO deleteCategory(int categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            return new ResponseDTO(404, "Category not found with id: " + categoryId, null);
        }
        categoryRepository.deleteById(categoryId);
        return new ResponseDTO(200, "Category deleted successfully", null);
    }
}