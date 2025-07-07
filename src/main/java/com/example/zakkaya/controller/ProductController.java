package com.example.zakkaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.zakkaya.service.ProductService;
import com.example.zakkaya.entity.*;
import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    //productId(商品IDで検索)
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable Integer productId) {
        Product product = productService.findProductByProductId(productId);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    //categoryId（カテゴリIDで検索）
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }
    

    
}
