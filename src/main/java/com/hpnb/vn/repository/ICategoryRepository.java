package com.hpnb.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hpnb.vn.models.CategoryModels;

public interface ICategoryRepository extends JpaRepository<CategoryModels, Long>{

    CategoryModels findByCategoryName(String categoryName);
    CategoryModels findByCategoryCode(String categoryCode);
	
}
