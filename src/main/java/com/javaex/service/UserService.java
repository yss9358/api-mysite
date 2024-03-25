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
	
}
