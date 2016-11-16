package com.bit2016.jblog.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.Category;

@Repository
public class CategoryDAO {
	@Autowired
	private SqlSession sqlSession;

	public Long insert(Category category) {
		sqlSession.insert("category.insert", category);
		return category.getNo();
	}

	public List<Category> selectAll(Long no) {
		List<Category> list = sqlSession.selectList("category.selectAll", no);
		return list;
	}

	public Category get(Long no) {

		return sqlSession.selectOne("category.get", no);
	}

	public List<Category> getName(Long no) {
		List<Category> list = sqlSession.selectList("category.getName", no);
		return list;
	}

	public Category getNameTop(Long no) {

		return sqlSession.selectOne("category.getNameTop", no);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}

}
