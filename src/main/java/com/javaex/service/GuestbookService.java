package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	// 전체 리스트 가져오기
	public List<GuestbookVo> exeList() {
		return guestbookDao.list();
	}
	
	// 등록
	public GuestbookVo exeAdd(GuestbookVo guestbookVo) {
		int count = guestbookDao.insertGuest(guestbookVo);
		if(count == 1) {
			return guestbookDao.selectOneByNo(guestbookVo.getNo());
		} else {
			return null;
		}
	}
	
}
