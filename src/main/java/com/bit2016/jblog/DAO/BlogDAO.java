package com.bit2016.jblog.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.Blog;

@Repository
public class BlogDAO {

	@Autowired
	private SqlSession sqlSession;

	public void insert(Blog blog) {
		sqlSession.insert("blog.insert", blog);
	}

	public void update(Blog blog) {
		sqlSession.update("blog.update", blog);
	}

	public Blog get(Long no) {
		return sqlSession.selectOne("blog.get", no);
	}

}
