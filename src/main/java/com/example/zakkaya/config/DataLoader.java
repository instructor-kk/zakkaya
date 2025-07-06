package com.example.zakkaya.config;

import java.util.*;
//import java.util.Locale.Category;
//import org.apache.catalina.startup.Catalina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.zakkaya.repositort.CategoryRepository;
import com.example.zakkaya.entity.Category;

@Component 
public class DataLoader implements CommandLineRunner{

    private final CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args){
        loadSampleCategories();
    }

    private void loadSampleCategories() {
        if(categoryRepository.count() > 0) {
            return; //すでにデータが存在する場合はスキップ
        }

        List<Category> categories = Arrays.asList(
            createCategory(
                "ホーム・キッチン", 
                "おしゃれで使い勝手のよいこだわりアイテムです。日々のお料理が楽しくなるアイテムを取り揃えております。" 
            ),
            createCategory(
                "ステーショナリー", 
                "きらりと個性が光る小さなアイテム。いつもの仕事にも、勉強のおともにも。"
            ));

        categoryRepository.saveAll(categories);
    }

    private Category createCategory(String name, String description){
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}


