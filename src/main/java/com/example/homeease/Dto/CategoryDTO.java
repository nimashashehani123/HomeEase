package com.example.homeease.Dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private int categoryId;
    private String categoryName; // Plumbing, Electrical, Cleaning, etc.
    private String image; // Path to the category image
}