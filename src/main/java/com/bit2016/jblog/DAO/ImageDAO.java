package com.bit2016.jblog.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.Blog;
import com.bit2016.jblog.vo.Image;

@Repository
public class ImageDAO {

	@Autowired
	private SqlSession sqlSession;

	public Long insert(Image image) {
		sqlSession.insert("image.insert", image);
		return image.getNo();
	}

	public List<Image> get(Image image) {
		return sqlSession.selectList("image.selectByPostNo", image);
	}
	
	public Image selectByNo(Long no){
		return sqlSession.selectOne("image.selectByNo", no);
	}

}
