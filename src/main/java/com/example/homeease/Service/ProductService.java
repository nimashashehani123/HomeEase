package com.example.homeease.Service;

import com.example.homeease.Dto.ResponseDTO;
import com.example.homeease.Dto.ProductDTO;

public interface ProductService {
    ResponseDTO addProduct(ProductDTO productDTO);
    ResponseDTO getAllProducts();
    ResponseDTO getProductById(int productId);
    ResponseDTO updateProduct(int productId, ProductDTO productDTO);
    ResponseDTO deleteProduct(int productId);
}