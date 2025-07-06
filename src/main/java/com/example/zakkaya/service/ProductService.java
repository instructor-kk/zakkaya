package com.example.zakkaya.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.zakkaya.repositort.*;
import com.example.zakkaya.entity.*;
import java.util.*;

public class ProductService {
private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Productを全件検索する
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    //productIdでProductを検索する
    public Product findProductByProductId(Integer productId) {
        Optional<Product> productOpt =  productRepository.findById(productId);
        return productOpt.orElse(null);
    }

    //categoryIdでProductを検索する
}
