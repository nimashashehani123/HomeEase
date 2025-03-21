package com.example.homeease.Repo;

import com.example.homeease.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Custom query methods can be added here
    Category findByCategoryName(String categoryName); // Find category by name
}