package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.TBoardVo;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 리스트 가져오기
	@GetMapping("/api/boards")
	public JsonResult list() {
		List<TBoardVo> list = boardService.exeList();
		if(list != null) {
			return JsonResult.success(list);
		} else {
			return JsonResult.fail("리스트를 불러오는데 실패했습니다.");
		}
	}
	
	// 한명 데이터 가져오기
	@GetMapping("/api/boards/read")
	public JsonResult selelctOne(@RequestParam int no) {
		TBoardVo voOne = boardService.exeSelectOneByNo(no);
		if(voOne != null) {
			return JsonResult.success(voOne);
		} else {
			return JsonResult.fail("정보를 가져오는데 실패했습니다.");
		}
	}
	
	
	// 등록
	@PostMapping("/api/boards")
	public JsonResult add(@RequestBody TBoardVo tBoardVo) {
		int count = boardService.exeAdd(tBoardVo);
		if(count == 1) {
			return JsonResult.success(count);
		} else {
			return JsonResult.fail("등록에 실패했습니다.");
		}
	}
	
	// 삭제
	@DeleteMapping("/api/boards")
	public JsonResult delete(@RequestBody int no) {
		int count = boardService.exeDelete(no);
		if(count == 1) {
			return JsonResult.success(count);
		} else {
			return JsonResult.fail("삭제에 실패했습니다.");
		}
	}
	
	
	
}
