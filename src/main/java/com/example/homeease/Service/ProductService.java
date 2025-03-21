package com.example.homeease.Service;

import com.example.homeease.Advisor.ResourceNotFoundException;
import com.example.homeease.Entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id) throws ResourceNotFoundException;
    Product updateProduct(int id, Product product) throws ResourceNotFoundException;
    void deleteProduct(int id) throws ResourceNotFoundException;
}