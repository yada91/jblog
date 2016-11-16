package com.bit2016.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.DAO.BlogDAO;
import com.bit2016.jblog.DAO.JusersDAO;
import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Jusers;

@Service
public class JuserService {

	@Autowired
	private JusersDAO jusersDAO;

	@Autowired
	private BlogDAO blogDao;

	public long join(Jusers vo) {
		return jusersDAO.insert(vo);
	}

	public void insert(Jusers jusers) {
		Blog blog = new Blog();
		blog.setNo(jusers.getNo());
		blog.setTitle(jusers.getId() + "의 블로그");
		blog.setLogo("spring-logo.jpg");
		blogDao.insert(blog);
	}

	public Jusers login(String id, String password) {
		Jusers authUser = jusersDAO.selectForLogin(id, password);
		return authUser;
	}

	public Jusers getNo(String id) {
		Jusers authUser = jusersDAO.idExist(id);
		return authUser;
	}

	public boolean idExist(String id) {
		if (jusersDAO.idExist(id) != null) {
			return true;
		}
		return false;
	}
}
