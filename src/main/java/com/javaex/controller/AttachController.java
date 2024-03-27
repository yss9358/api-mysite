package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;

@RestController
public class AttachController {

	@Autowired
	private AttachService attachService;
	
	// 파일 등록하기
	@PostMapping("/api/attach")
	public JsonResult upload(@RequestParam MultipartFile file) {
		// 파일 확인
		if(file != null) { // 파일이 있으면 업로드 실행
			String saveName = attachService.exeUpload(file);
			if(saveName != null) { // 실행된 결과값이 null이 아니면 성공, null 이면 실패결과 보내기
				return JsonResult.success(saveName);
			} else {
				return JsonResult.fail("등록에 실패했습니다.");
			}
		} else { // 파일이 없을경우 실패메세지 보내기
			return JsonResult.fail("파일이 없습니다.");
		}

	} // 파일등록 끝
	
}
