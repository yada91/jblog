package com.bit2016.jblog.DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.Jusers;

@Repository
public class JusersDAO {

	@Autowired
	private SqlSession sqlSession;

	public Long insert(Jusers vo) {
		sqlSession.insert("jusers.insert", vo);
		return vo.getNo();
	}

	public Jusers selectForLogin(String id, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		Jusers authUser = sqlSession.selectOne("jusers.selectForLogin", map);
		return authUser;
	}

	public Jusers idExist(String id) {
		Jusers authUser = sqlSession.selectOne("jusers.idExist", id);
		return authUser;
	}

}
