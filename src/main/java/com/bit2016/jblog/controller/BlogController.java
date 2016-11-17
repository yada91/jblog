package com.bit2016.jblog.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bit2016.jblog.vo.Image;
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
		return "redirect:/" + id;
	}

	@Auth
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable String id, @AuthUser Jusers authUser, Model model) {
		Blog blog = blogService.getById(id);
		model.addAttribute("blog", blog);
		model.addAttribute("no", authUser.getNo());
		return "blog/blog-admin-category";
	}

	@RequestMapping("/blog/{no}/image")
	public void image(@PathVariable("no") Long no, HttpServletResponse response) throws IOException {
		Image image = blogService.selectByNo(no);
		if (image == null)
			return;
		String fileName = URLEncoder.encode(image.getSaveName(), "UTF-8");
		response.setContentType(image.getMimeType());
		response.setHeader("Content-Disposition", "filename=" + fileName + ";");
		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			output.write(image.getData());
		}
	}

	@Auth
	@RequestMapping(value = "/blog/seupload", method = RequestMethod.POST)
	public void image(HttpServletRequest request, HttpServletResponse response, @AuthUser Jusers authUser) {

		try {
			String fileName = request.getHeader("file-name");
			int fileSize = Integer.parseInt(request.getHeader("file-size"));
			InputStream input = request.getInputStream();
			int count = 0;
			byte[] data = new byte[fileSize];
			while (count < fileSize)
				count += input.read(data, count, data.length - count);

			Image image = new Image();
			image.setUserNo(authUser.getNo());
			image.setPostNo(blogService.lastPostNo());
			image.setSaveName(fileName);
			image.setFileSize(fileSize);
			image.setData(data);
			Long no = blogService.insertImage(image);

			String s = "&bNewLine=true&sFileName=" + fileName;
			s += "&sFileURL=/jblog/blog/" + no + "/image";
			PrintWriter out = response.getWriter();
			out.print(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
