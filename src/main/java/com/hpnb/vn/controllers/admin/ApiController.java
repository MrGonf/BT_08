package com.hpnb.vn.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hpnb.vn.models.CategoryModels;
import com.hpnb.vn.services.ICategoryService;

@RestController
@RequestMapping("/admin/category/api")
public class ApiController {

    @Autowired
    private ICategoryService categoryService;

    // Lấy tất cả categories
    @GetMapping
    public ResponseEntity<List<CategoryModels>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    // Thêm mới category
    @PostMapping
    public ResponseEntity<CategoryModels> create(@RequestBody CategoryModels category) {
        CategoryModels created = categoryService.save(category);
        return ResponseEntity.status(201).body(created);
    }

    // Cập nhật category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModels> update(@PathVariable Long id, @RequestBody CategoryModels category) {
        CategoryModels existing = categoryService.findById(id);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        category.setCategoryId(id);
        CategoryModels updated = categoryService.update(category);
        return ResponseEntity.ok(updated);
    }

    // Xóa category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CategoryModels existing = categoryService.findById(id);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        categoryService.delete(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }
}
