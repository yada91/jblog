package com.bit2016.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.DAO.CategoryDAO;
import com.bit2016.jblog.vo.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;

	public Long insertCategory(Category category) {
		return categoryDAO.insert(category);
	}

	public List<Category> selectAll(Long no) {
		List<Category> list = categoryDAO.selectAll(no);
		return list;
	}

	public Category get(Long no) {
		Category category = categoryDAO.get(no);
		return category;
	}

	public Category getNameTop(Long no) {
		Category category = categoryDAO.getNameTop(no);
		return category;
	}

	public List<Category> getName(Long no) {
		List<Category> list = categoryDAO.getName(no);
		return list;
	}

	public void delete(Long no) {
		categoryDAO.delete(no);
	}
}
