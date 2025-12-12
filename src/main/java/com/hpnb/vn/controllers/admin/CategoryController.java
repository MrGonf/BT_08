package com.hpnb.vn.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hpnb.vn.models.CategoryModels;
import com.hpnb.vn.services.ICategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/category")
	public String index(Model model) {
		List<CategoryModels> list = this.categoryService.getAll();
		model.addAttribute("list", list);
		return "admin/category/index";
	}
}


//ALTER TABLE `jpa`.`category` AUTO_INCREMENT = 1;
