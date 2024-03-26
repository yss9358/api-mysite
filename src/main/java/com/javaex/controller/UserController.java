package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
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
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		UserVo authUser = userService.exeLogin(userVo);
		if(authUser != null) { // 로그인에 성공하면 토큰을 발급해서 응답문서의 헤더에 실어 보낸다.
			// 숫자열을 문자열로 바꿀때 앞에 ""+숫자열 을 작성하면 문자열로 바꾼다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());// jwt 토큰 발급하고 헤더에 추가
			return JsonResult.success(authUser);
		} else {
			return JsonResult.fail("아이디와 비밀번호를 확인하세요.");
		}
	}
	
	// 회원가입 
	@PostMapping("/api/users/join")
	public JsonResult join(@RequestBody UserVo userVo) {
		int count = userService.exeJoin(userVo);
		if(count != -1) {
			return JsonResult.success(count);
		} else {
			return JsonResult.fail("회원가입에 실패했습니다.");
		}
		
	}
	
	// 회원정보 수정폼
	@GetMapping("/api/users/modify")
	public JsonResult modifyForm(HttpServletRequest request) {
		int no = JwtUtil.getNoFromHeader(request); // request로 온 토큰을 검증하고 no값을 꺼낸다.
		if(no != -1) {
			return JsonResult.success(userService.exeModifyform(no));
		} else {
			return JsonResult.fail("정보가 유효하지 않습니다.");
		}
	}
	
	// 회원정보 수정
	@PutMapping("/api/users/modify")
	public JsonResult modify(HttpServletRequest request, @RequestBody UserVo userVo) {
		if(JwtUtil.getNoFromHeader(request) != -1) {
			return JsonResult.success(userService.exeModify(userVo));
		} else {
			return JsonResult.fail("로그인 하지않음");
		}
	}
	
}
