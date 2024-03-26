package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	// 전체 리스트 가져오기
	@GetMapping("/api/guests")
	public JsonResult list() {
		List<GuestbookVo> guestbookList = guestbookService.exeList();
		if(guestbookList != null) {
			return JsonResult.success(guestbookList);
		} else {
			return JsonResult.fail("리스트를 가져오는데 실패했습니다.");
		}
	}
	// 등록
	@PostMapping("/api/guests")
	public JsonResult Add(@RequestBody GuestbookVo guestbookVo) {
		GuestbookVo vo = guestbookService.exeAdd(guestbookVo);
		if(vo != null) {
			return JsonResult.success(vo);
		} else {
			return JsonResult.fail("등록에 실패했습니다.");
		}
	}
	
	// 삭제
}
