package com.bit2016.jblog.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.Post;

@Repository
public class PostDAO {
	@Autowired
	private SqlSession sqlSession;

	public void insert(Post post) {
		sqlSession.insert("post.insert", post);
	}

	public List<Post> selectAll(Long categoryNo) {
		List<Post> list = sqlSession.selectList("post.selectAll",categoryNo);
		return list;
	}

	public Post selectTop(Long categoryNo) {
		return sqlSession.selectOne("post.selectTop",categoryNo);
	}

	public Post selectOne(Long no) {
		return sqlSession.selectOne("post.selectOne",no);
	}
}
