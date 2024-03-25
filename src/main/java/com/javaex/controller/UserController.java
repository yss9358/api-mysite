package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// 로그인
	@PostMapping("/api/users/login")
	public UserVo login(@RequestBody UserVo userVo, HttpServletResponse response) {
		UserVo authUser = userService.exeLogin(userVo);
		if(authUser != null) { // 로그인에 성공하면 토큰을 발급해서 응답문서의 헤더에 실어 보낸다.
			// jwt 토큰 발급하고 헤더에 추가
			// 숫자열을 문자열로 바꿀때 앞에 ""+숫자열 을 작성하면 문자열로 바꾼다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
		} 
		return authUser;
	}
	
	
}
