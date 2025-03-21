package com.example.homeease.Service;

import com.example.homeease.Entity.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product updateProduct(int id, Product product);
    void deleteProduct(int id);
}