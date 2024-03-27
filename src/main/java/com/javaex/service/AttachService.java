package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {

	@Autowired
	private AttachDao attachDao;
	
	// 파일 업로드
	public String exeUpload(MultipartFile file, int userNo) {
		
		// 파일저장디렉토리
		String saveDir = "C:\\javaStudy\\uploadfile";

		// (1)파일관련 정보 추출///////////////////////////////////////////////////

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));

		// 저장파일명(겹치지 않아야 된다)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일사이즈
		long fileSize = file.getSize();

		// 파일전체경로
		String filePath = saveDir + "\\" + saveName;
	
		
		// (2)파일저장(서버쪽 하드디스크에 저장)///////////////////////////////////////////////////
		try {
			byte[] fileData;
			fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			bos.write(fileData);
			bos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		// vo로묶기 -> DB저장
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize, userNo);
		int count = attachDao.insertFile(attachVo);
		if(count == 1) {
			return saveName;
		} else {
			return null;
		}
		
	}// 파일 업로드 끝
}
