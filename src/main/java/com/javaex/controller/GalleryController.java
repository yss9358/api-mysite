package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.AttachVo;

import jakarta.servlet.http.HttpServletRequest;

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
	
	// 등록
	@PostMapping("/api/galleries")
	public JsonResult add(HttpServletRequest request, MultipartFile file,@RequestParam String content) {
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) {
			Map<String, Object> imgMap= galleryService.exeAdd(file,no,content);
			return JsonResult.success(imgMap);
		} else {
			return JsonResult.fail("등록에 실패했습니다.");
		}
	}
	
}
