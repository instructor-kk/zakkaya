package com.example.zakkaya.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    
    //外部キーとしてCategoryを指定
    @ManyToOne
    @JoinColumn(name = "category_Id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Column(nullable = false)
    private Integer price;
    
    @Column(nullable = false)
    private Integer stockQuantity;
    
    private String imageUrl;
    
    private Boolean isRecommended;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    private LocalDateTime saleStartDate;

    private LocalDateTime saleEndDate;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}