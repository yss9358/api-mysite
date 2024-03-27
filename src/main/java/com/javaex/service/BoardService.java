package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.TBoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	// 리스트 가져오기
	public List<TBoardVo> exeList() {
		return boardDao.list();
	}
	
	// 한명 데이터 가져오기
	public TBoardVo exeSelectOneByNo(int no) {
		return boardDao.selectOneByNo(no);
	}
	
	// 등록
	public int exeAdd(TBoardVo tBoardVo) {
		return boardDao.insertBoard(tBoardVo);
	}
	
	// 삭제
	public int exeDelete(int no) {
		return boardDao.deleteByNo(no);
	}
}
