package com.example.zakkaya.service;

import com.example.zakkaya.repositort.*;
import com.example.zakkaya.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    //カテゴリを全件検索する
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    
}
