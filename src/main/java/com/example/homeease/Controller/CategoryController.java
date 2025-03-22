package com.example.homeease.Controller;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.CategoryDTO;
import com.example.homeease.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        ResponseDTO response = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllCategories() {
        ResponseDTO response = categoryService.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> getCategoryById(@PathVariable int categoryId) {
        ResponseDTO response = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> updateCategory(@PathVariable int categoryId, @RequestBody CategoryDTO categoryDTO) {
        ResponseDTO response = categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable int categoryId) {
        ResponseDTO response = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}