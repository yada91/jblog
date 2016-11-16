package com.bit2016.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.DAO.BlogDAO;
import com.bit2016.jblog.DAO.CategoryDAO;
import com.bit2016.jblog.DAO.JusersDAO;
import com.bit2016.jblog.DAO.PostDAO;
import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Category;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.jblog.vo.Post;

@Service
public class PostService {

	@Autowired
	private BlogDAO blogDao;
	@Autowired
	private PostDAO postDao;
	@Autowired
	private JusersDAO juserDao;
	@Autowired
	private CategoryDAO categoryDao;

	public void insert(Post post) {
		postDao.insert(post);
	}

	public List<Post> selectAll(Long categoryNo) {
		List<Post> list = postDao.selectAll(categoryNo);
		return list;
	}

	public Post selectTop(Long categoryNo) {
		return postDao.selectTop(categoryNo);
	}

	public Post selectOne(Long no) {

		return postDao.selectOne(no);
	}

	public List<Category> getName(Long no) {
		List<Category> list = categoryDao.getName(no);
		return list;
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
