package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.vo.AttachVo;

@RestController
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	// 리스트 가져오기
	@GetMapping("/api/galleries")
	public JsonResult list() {
		List<AttachVo> list = galleryService.exeList();
		if(list != null) {
			return JsonResult.success(list);
		} else {
			return JsonResult.fail("파일을 불러오는데 실패했습니다.");
		}
		
	}
	
}
