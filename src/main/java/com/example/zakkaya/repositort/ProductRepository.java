package com.example.zakkaya.repositort;


import com.example.zakkaya.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    //categoryIdから商品を検索
    List<Product> findProductsByCategoryId(Integer categoryId);


    //ここはあとで修正する箇所
    // @Modifying
    // @Query("UPDATE Product p SET p.stock = p.stock - ?2 WHERE p.productId = ?1 AND p.stock >= ?2")
    // int decreaseStock(Integer productId, Integer quantity);
}