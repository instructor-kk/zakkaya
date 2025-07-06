package com.example.zakkaya.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.zakkaya.entity.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    //デフォルトでOK
    
} 