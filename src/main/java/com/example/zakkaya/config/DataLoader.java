package com.example.zakkaya.config;

import java.time.LocalDateTime;
import java.util.*;
//import java.util.Locale.Category;
//import org.apache.catalina.startup.Catalina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.zakkaya.repositort.CategoryRepository;
import com.example.zakkaya.repositort.ProductRepository;
import com.example.zakkaya.entity.*;

@Component 
public class DataLoader implements CommandLineRunner{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, 
            ProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args){
        loadSampleCategories();
        loadSampleProducts();
System.out.println("kokomade32");        
        loadSampleProducts();
    }

    //Categoriesテーブルにサンプルデータをロードする
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

    private void loadSampleProducts() {
        if (productRepository.count() > 0) {
            return; // すでにデータが存在する場合はスキップ
        }

        List<Product> products = Arrays.asList(
            createProduct(
                "シンプルデスクオーガナイザー", 
                "机の上をすっきり整理できる木製オーガナイザー。ペン、メモ、スマートフォンなどを収納できます。", 
                3500, 
                20, 
                "/images/desk-organizer.png", 
                true
            ),
            createProduct(
                "アロマディフューザー（ウッド）", 
                "天然木を使用したシンプルなデザインのアロマディフューザー。LEDライト付き。", 
                4200, 
                15, 
                "/images/aroma-diffuser.png", 
                true
            ),
            createProduct(
                "コットンブランケット", 
                "オーガニックコットン100%のやわらかブランケット。シンプルなデザインで様々なインテリアに合います。", 
                5800, 
                10, 
                "/images/cotton-blanket.png", 
                false
            ),
            createProduct(
                "ステンレスタンブラー", 
                "保温・保冷機能に優れたシンプルなデザインのステンレスタンブラー。容量350ml。", 
                2800, 
                30, 
                "/images/tumbler.png", 
                false
            ),
            createProduct(
                "ミニマルウォールクロック", 
                "余計な装飾のないシンプルな壁掛け時計。静音設計。", 
                3200, 
                25, 
                "/images/wall-clock.png", 
                false
            ),
            createProduct(
                "リネンクッションカバー", 
                "天然リネン100%のクッションカバー。取り外して洗濯可能。45×45cm対応。", 
                2500, 
                40, 
                "/images/cushion-cover.png", 
                true
            ),
            createProduct(
                "陶器フラワーベース", 
                "手作りの風合いが魅力の陶器製フラワーベース。シンプルな形状で花を引き立てます。", 
                4000, 
                15, 
                "/images/flower-vase.png", 
                false
            ),
            createProduct(
                "木製コースター（4枚セット）", 
                "天然木を使用したシンプルなデザインのコースター。4枚セット。", 
                1800, 
                50, 
                "/images/wooden-coaster.png", 
                false
            ),
            createProduct(
                "キャンバストートバッグ", 
                "丈夫なキャンバス地で作られたシンプルなトートバッグ。内ポケット付き。", 
                3600, 
                35, 
                "/images/tote-bag.png", 
                true
            ),
            createProduct(
                "ガラス保存容器セット", 
                "電子レンジ・食洗機対応のガラス製保存容器。3サイズセット。", 
                4500, 
                20, 
                "/images/glass-container.png", 
                false
            )
        );
        
        productRepository.saveAll(products);
    }

    private Product createProduct(String name, String description, Integer price, Integer stock, String imageUrl, Boolean isRecommended) {
        
        //カテゴリCategoryを作成
        Category category = new Category();
        category.setId(1);
        category.setName("ホーム・キッチン");
        category.setDescription("おしゃれで使い勝手のよいこだわりアイテムです。日々のお料理が楽しくなるアイテムを取り揃えております。");
        
        //Productを作成
        Product product  = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setStockQuantity(stock);
        product.setImageUrl(imageUrl);
        product.setIsRecommended(isRecommended);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

}


