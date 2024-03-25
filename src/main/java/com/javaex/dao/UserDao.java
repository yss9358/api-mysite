package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 로그인
	public UserVo userSelectByIdPw(UserVo userVo) {
		return sqlSession.selectOne("user.userSelectByIdPw", userVo);
	}
	
}
