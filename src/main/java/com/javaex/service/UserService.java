package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	// 로그인
	public UserVo exeLogin(UserVo userVo) {
		return userDao.userSelectByIdPw(userVo);
	}
	
	// 회원정보 수정폼 - 한명 데이터 가져오기
	public UserVo exeModifyform(int no) {
		return userDao.userSelectOneByNo(no);
	}
	
	// 회원정보 수정
	public int exeModify(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
}
