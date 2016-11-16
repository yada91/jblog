package com.bit2016.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.jblog.DTO.JSONResult;
import com.bit2016.jblog.service.CategoryService;
import com.bit2016.jblog.vo.Category;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Auth
	@ResponseBody
	@RequestMapping("/blog/api/list")
	public JSONResult list(@RequestParam(value = "no", required = true) Long no) {

		List<Category> list = categoryService.selectAll(no);

		return JSONResult.success(list);
	}

	@Auth
	@ResponseBody
	@RequestMapping("/blog/api/insert")
	public JSONResult insert(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = true) String description, @AuthUser Jusers jusers) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setUserNo(jusers.getNo());

		Long no = categoryService.insertCategory(category);
		Category c1 = categoryService.get(no);
		return JSONResult.success(c1);
	}

	@Auth
	@ResponseBody
	@RequestMapping("/blog/api/delete")
	public JSONResult delete(@RequestParam(value = "no", required = true) Long no, @AuthUser Jusers jusers) {
		categoryService.delete(no);
		return JSONResult.success(no);
	}
}
