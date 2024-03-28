package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.AttachVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	// 리스트 가져오기
	public List<AttachVo> exeList() {
		return galleryDao.list();
	}
	
	// 등록
	public Map<String,Object> exeAdd(MultipartFile file, int no, String content) {		
		// 파일 저장
		String saveDir = "C:\\javaStudy\\uploadfile";
		
		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 저장파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일 사이즈
		long fileSize = file.getSize();
		
		// 파일전체 경로
		String filePath = saveDir + "\\" + saveName;
		if(file != null) {
			// 파일이 null이 아니면 저장 
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
		}
		// 값들을 vo로 묶어서 dao로 보냄
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize, no, content);
		int count = galleryDao.insertImage(attachVo);
		if(count == 1) {
			AttachVo getVo = galleryDao.selectOneData(attachVo.getNo());
			Map<String, Object> imgMap = new HashMap<String,Object>();
			imgMap.put("name", getVo.getName());
			imgMap.put("saveName", getVo.getSaveName());
			imgMap.put("userNo", getVo.getUserNo());
			return imgMap;
		} else {
			return null;
		}
	}
}
