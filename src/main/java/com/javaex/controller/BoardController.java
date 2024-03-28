package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.TBoardVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 리스트 가져오기
	@GetMapping("/api/boards")
	public JsonResult list() {
		if(boardService.exeList() != null) {
			return JsonResult.success(boardService.exeList());
		} else {
			return JsonResult.fail("리스트를 불러오는데 실패했습니다.");
		}
	}
	
	// 글 읽기 - 한명 데이터 가져오기
	@GetMapping("/api/boards/read")
	public JsonResult selelctOne(@RequestParam int no) {
		TBoardVo voOne = boardService.exeSelectOneByNo(no);
		if(voOne != null) {
			return JsonResult.success(voOne);
		} else {
			return JsonResult.fail("정보를 가져오는데 실패했습니다.");
		}
	}
	
	// 등록 - 토큰검증
	@PostMapping("/api/boards")
	public JsonResult add(@RequestBody TBoardVo tBoardVo, HttpServletRequest request) {
		if(JwtUtil.getNoFromHeader(request) != -1) {
			if(boardService.exeAdd(tBoardVo) == 1) {
				return JsonResult.success(boardService.exeAdd(tBoardVo));
			} else {
				return JsonResult.fail("등록에 실패했습니다.");
			}
		} else {
			return JsonResult.fail("토큰이 유효하지 않습니다.");
		}
	}
	
	// 삭제 - 토큰검증
	@DeleteMapping("/api/boards")
	public JsonResult delete(@RequestBody int no, HttpServletRequest request) {
		if(JwtUtil.getNoFromHeader(request) != -1) {
			if(boardService.exeDelete(no) == 1) {
				return JsonResult.success(boardService.exeDelete(no));
			} else {
				return JsonResult.fail("삭제에 실패했습니다.");
			}
		} else {
			return JsonResult.fail("토큰이 유효하지 않습니다.");
		}
	}
	
	// 수정폼 - 한명데이터 가져오기
	@GetMapping("/api/boards/modify")
	public JsonResult modifyForm(HttpServletRequest request,@RequestParam int no) {
		int tokenNo = JwtUtil.getNoFromHeader(request);
		if(tokenNo != -1) {
			TBoardVo vo = boardService.exeModifyForm(no);
			return JsonResult.success(vo);
		} else {
			return JsonResult.fail("토큰이 유효하지 않습니다.");
		}
	}
	
	// 수정 - 토큰검증
	@PutMapping("/api/boards/modify")
	public void modify(HttpServletRequest request,@RequestBody TBoardVo tboardVo) {
		int no = JwtUtil.getNoFromHeader(request);
		System.out.println(no);
		boardService.exeModify(tboardVo);
		
	}
	
	
	
}
