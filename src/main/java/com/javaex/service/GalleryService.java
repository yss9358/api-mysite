package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
