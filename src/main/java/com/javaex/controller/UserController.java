package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
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
	
	// 회원정보 수정폼
	@GetMapping("/api/users/modify")
	public UserVo modifyForm(HttpServletRequest request) {
		/*
		// 토큰 꺼내기
		String token = JwtUtil.getTokenByHeader(request);
		System.out.println(token);
		
		// 꺼내온 토큰 검증
		boolean check = JwtUtil.checkToken(token);
		System.out.println(check);
		
		if(check==true) {
			System.out.println("정상");
			int no = Integer.parseInt(JwtUtil.getSubjectFromToken(token));
			System.out.println(no);
			
		} else {
			System.out.println("비정상");
		}
		*/
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) {
			// 정상일경우
			UserVo userVo = userService.exeModifyform(no);
//			System.out.println(userVo);
			return userVo;
		} else {
			return null;
		}
		
	}
	
	// 회원정보 수정
	@PutMapping("/api/users/modify")
	public int modify(HttpServletRequest request, @RequestBody UserVo userVo) {
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) {
			int count = userService.exeModify(userVo);
			return count;
		} else {
			return -1;
		}
		
	}
	
	
}
