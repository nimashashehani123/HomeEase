package com.example.homeease.Service.Impl;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Entity.Category;
import com.example.homeease.Repo.CategoryRepository;
import com.example.homeease.Service.CategoryService;
import com.example.homeease.Utill.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${app.base-url}") // Configure in application.properties
    private String baseUrl;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public int addCategory(CategoryDTO categoryDTO) {
        try {
            if (!(categoryRepository.findByCategoryName(categoryDTO.getCategoryName()) == null)) {
                return VarList.Not_Acceptable;
            }else {

                // Ensure required fields are present
                if (categoryDTO.getCategoryName() == null || categoryDTO.getCategoryName().trim().isEmpty()) {
                    return VarList.Bad_Request; // 400 - Missing name
                }

                // Manual mapping to ensure all fields are set
                Category category = new Category();
                category.setCategoryName(categoryDTO.getCategoryName());
                category.setImage(categoryDTO.getImage());
                // Set all other necessary fields...

                // Debug logging
                System.out.println("Saving category: " + category);

                Category savedCategory = categoryRepository.save(category);

                // Verify save operation
                if (savedCategory.getCategoryId() != 0) {
                    System.out.println("Saved successfully with ID: " + savedCategory.getCategoryId());
                    return VarList.Created; // 201 - Success
                } else {
                    System.out.println("Save operation failed");
                    return VarList.Internal_Server_Error; // 500 - Save failed
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in addCategory: " + e.getMessage());
            e.printStackTrace();
            return VarList.Internal_Server_Error; // 500
        }
    }


    @Override
    public ResponseDTO getCategoryIdByName(String categoryName) {
        try {
            int idByCategoryName = categoryRepository.findIdByCategoryName(categoryName);
            if (idByCategoryName == 0) {
                return new ResponseDTO(VarList.Bad_Request, "No categories found", null);
            }
            return new ResponseDTO(VarList.OK, "Success", idByCategoryName);
        }catch (Exception e) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Internal server error", null);
        }
    }

    @Override
    public ResponseDTO getAllCategoryIds() {
        try {
            List<String> categoryIds = categoryRepository.findAllCategoryIds();

            if (categoryIds.isEmpty()) {
                return new ResponseDTO(VarList.Bad_Request, "No categories found", null);
            }

            return new ResponseDTO(VarList.OK, "Success", categoryIds);

        } catch (Exception e) {
            return new ResponseDTO(VarList.Internal_Server_Error, "Internal server error", null);
        }
    }

    // In your CategoryService.java
    @Override
    public ResponseDTO getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryId(category.getCategoryId());
            dto.setCategoryName(category.getCategoryName());

            // Fix image URL construction
            if (category.getImage() != null) {
                dto.setImage(category.getImage());
            } else {
                dto.setImage("");
            }

            dtos.add(dto);
        }

        return new ResponseDTO(200, "Success", dtos);
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