package com.bit2016.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.jblog.service.PostService;
import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Category;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.jblog.vo.Post;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Auth
	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.GET)
	public String main(@PathVariable String id, @ModelAttribute Post post, Model model, @AuthUser Jusers authUser) {
		long no = authUser.getNo();
		Blog blog = postService.getById(id);
		List<Category> list = postService.getName(no);
		model.addAttribute("id", id);
		model.addAttribute("list", list);
		model.addAttribute("blog", blog);
		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.POST)
	public String insert(@AuthUser Jusers authUser, @ModelAttribute Post post) {
		String id = authUser.getId();
		postService.insert(post);
		return "redirect:/" + id;
	}
	
	
}
