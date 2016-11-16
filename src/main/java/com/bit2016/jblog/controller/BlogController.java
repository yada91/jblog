package com.bit2016.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.jblog.service.BlogService;
import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping("/{id}")
	public String main(@PathVariable String id, Model model) {

		Map<String, Object> map = blogService.index(id);
		Blog blog = blogService.getById(id);
		if (map == null) {
			model.addAttribute("blog", blog);
			return "blog/blog-main";
		}

		model.addAttribute("map", map);
		model.addAttribute("blog", blog);
		model.addAttribute("id", id);
		return "blog/blog-main";
	}

	@RequestMapping("/{id}/{cno}")
	public String main(@PathVariable String id, @PathVariable Long cno,
			@RequestParam(value = "pno", required = true, defaultValue = "0") Long pno, Model model) {
		Map<String, Object> map = blogService.index(id, cno, pno);
		Blog blog = blogService.getById(id);
		if (map == null) {
			model.addAttribute("blog", blog);
			return "blog/blog-main";
		}
		model.addAttribute("map", map);
		model.addAttribute("blog", blog);
		model.addAttribute("id", id);
		return "blog/blog-main";
	}

	@Auth
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.GET)
	public String basic(@PathVariable String id, Model model) {
		Blog blog = blogService.getById(id);
		model.addAttribute("blog", blog);

		return "blog/blog-admin-basic";
	}

	@Auth
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable String id, @RequestParam("file") MultipartFile file, @ModelAttribute Blog blog) {
		blogService.restore(file, blog);
		return "blog/blog-admin-basic";
	}

	@Auth
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable String id, @AuthUser Jusers authUser, Model model) {
		Blog blog = blogService.getById(id);
		model.addAttribute("blog", blog);
		model.addAttribute("no", authUser.getNo());
		return "blog/blog-admin-category";
	}

}
