package com.example.homeease.Controller;

import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/update")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/all")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}