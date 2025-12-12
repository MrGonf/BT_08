package com.hpnb.vn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpnb.vn.models.CategoryModels;
import com.hpnb.vn.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryModels> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean create(CategoryModels cateModel) {
        try {
            categoryRepository.save(cateModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CategoryModels findById(Long id) {
        Optional<CategoryModels> opt = categoryRepository.findById(Long.valueOf(id));
        return opt.orElse(null);
    }

    @Override
    public CategoryModels findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public CategoryModels findByCode(String code) {
        return categoryRepository.findByCategoryCode(code);
    }

    @Override
    public CategoryModels update(CategoryModels cateModel) {
        Optional<CategoryModels> opt = categoryRepository.findById(cateModel.getCategoryId());
        if (opt.isPresent()) {
            CategoryModels existing = opt.get();
            existing.setCategoryName(cateModel.getCategoryName());
            existing.setCategoryCode(cateModel.getCategoryCode());
            existing.setImages(cateModel.getImages());
            existing.setStatus(cateModel.getStatus());
            existing.setUser(cateModel.getUser());
            return categoryRepository.save(existing);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            categoryRepository.deleteById(Long.valueOf(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public CategoryModels save(CategoryModels category) {
	    try {
	        return categoryRepository.save(category);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
