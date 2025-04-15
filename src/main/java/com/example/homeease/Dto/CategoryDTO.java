package com.example.homeease.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryDTO {
    private int categoryId;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Category name can only contain alphanumeric characters and spaces")
    private String categoryName;

    private String image;
}