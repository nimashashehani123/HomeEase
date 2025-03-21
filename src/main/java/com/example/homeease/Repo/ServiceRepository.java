package com.example.homeease.Repo;
import com.example.homeease.Entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

    // Find services by category ID
    List<Service> findByCategory_CategoryId(int categoryId);

    // Find services by service provider ID
    List<Service> findByServiceProvider_UserId(int providerId);
}