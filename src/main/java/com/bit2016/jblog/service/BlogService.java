package com.bit2016.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.jblog.DAO.BlogDAO;
import com.bit2016.jblog.DAO.CategoryDAO;
import com.bit2016.jblog.DAO.JusersDAO;
import com.bit2016.jblog.DAO.PostDAO;
import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Category;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.jblog.vo.Post;

@Service
public class BlogService {

	private static final String SAVE_PATH = "c:\\upload";

	@Autowired
	private BlogDAO blogDao;
	@Autowired
	private PostDAO postDao;
	@Autowired
	private JusersDAO juserDao;
	@Autowired
	private CategoryDAO categoryDao;

	public void insert(Jusers jusers) {
		Blog blog = new Blog();
		blog.setNo(jusers.getNo());
		blog.setTitle(jusers.getId() + "의 블로그");
		blog.setLogo("spring-logo.jpg");
		blogDao.insert(blog);
	}

	public void restore(MultipartFile file, Blog blog) {
		String saveFileName = "";
		try {
			if (file.isEmpty() == true) {
				return;
			}

			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf('.') + 1);
			saveFileName = generateSaveFileName(extName);

			writeFile(file, saveFileName);
			blog.setLogo(saveFileName);
			System.out.println(blog);
			blogDao.update(blog);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "." + extName;
		return fileName;
	}

	private void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] fileData = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}

	public Map<String, Object> index(String id) {
		Long no = null;
		Jusers authUser = juserDao.idExist(id);
		if (authUser != null) {
			no = authUser.getNo();
		}

		Long categoryNo = null;
		Category category = categoryDao.getNameTop(no);
		if (category != null) {
			categoryNo = category.getNo();
		}

		Map<String, Object> map = null;
		if (no == null || categoryNo == null) {
			return map;
		} else {
			map = new HashMap<String, Object>();
			Post post = postDao.selectTop(categoryNo);
			List<Post> postList = postDao.selectAll(categoryNo);
			List<Category> cateList = categoryDao.getName(no);

			map.put("post", post);
			map.put("postlist", postList);
			map.put("catelist", cateList);

			return map;
		}

	}

	public Map<String, Object> index(String id, Long categoryNo, Long postNo) {

		Long no = null;
		Jusers authUser = juserDao.idExist(id);
		if (authUser != null) {
			no = authUser.getNo();
		}

		Map<String, Object> map = null;
		Post post = null;
		if (no == null || categoryNo == null) {
			return map;
		} else {
			map = new HashMap<String, Object>();
			if (postNo == 0) {
				post = postDao.selectTop(categoryNo);
			} else {
				post = postDao.selectOne(postNo);
			}

			List<Post> postList = postDao.selectAll(categoryNo);
			List<Category> cateList = categoryDao.getName(no);

			map.put("post", post);
			map.put("postlist", postList);
			map.put("catelist", cateList);

			return map;
		}

	}

	public Blog getById(String id) {
		Long no = null;
		Jusers authUser = juserDao.idExist(id);
		if (authUser != null) {
			no = authUser.getNo();
		}
		Blog blog = blogDao.get(no);
		return blog;
	}
}
