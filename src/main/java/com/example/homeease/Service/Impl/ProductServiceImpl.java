package com.example.homeease.Service.Impl;

import com.example.homeease.Entity.Product;
import com.example.homeease.Repo.ProductRepository;
import com.example.homeease.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());
            existingProduct.setContactNumber(product.getContactNumber());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}