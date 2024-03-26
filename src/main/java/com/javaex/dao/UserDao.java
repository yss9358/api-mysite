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
	
	// 회원가입
	public int insertUser(UserVo userVo) {
		return sqlSession.insert("user.insertUser",userVo);
	}
	
	// 회원정보 수정폼 - 한명 데이터 가져오기
	public UserVo userSelectOneByNo(int no) {
		return sqlSession.selectOne("user.userSelectOneByNo", no);
	}
	
	// 회원정보 수정
	public int updateUser(UserVo userVo) {
		return sqlSession.update("user.updateUser",userVo);
		
	}
}
