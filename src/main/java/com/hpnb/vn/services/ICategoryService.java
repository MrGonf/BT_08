package com.hpnb.vn.services;

import java.util.List;

import com.hpnb.vn.models.CategoryModels;

public interface ICategoryService {

	List<CategoryModels> getAll();
	Boolean create(CategoryModels cateModel);
	CategoryModels findById(Long id);
	CategoryModels findByName(String name);
	CategoryModels findByCode(String code);

	CategoryModels update(CategoryModels cateModel);
	Boolean delete(Long id);
	CategoryModels save(CategoryModels category);
}
