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
	
	// 추가 리스트 가져오기
	public List<TBoardVo> exeMoreList(int no) {
		return boardDao.moreList(no);
	}
	
	// 한명 데이터 가져오기
	public TBoardVo exeSelectOneByNo(int no) {
		int count = boardDao.hitUpdateByNo(no);
		if(count == 1) {
			 return boardDao.selectOneByNo(no);
		} else {
			return null;
		}
	}
	
	// 등록
	public int exeAdd(TBoardVo tBoardVo) {
		return boardDao.insertBoard(tBoardVo);
	}
	
	// 삭제
	public int exeDelete(int no) {
		return boardDao.deleteByNo(no);
	}
	
	// 수정폼 - 한명데이터 가져오기
	public TBoardVo exeModifyForm(int no) {
		return boardDao.selectOneByNo(no);
	}
	
	// 수정
	public int exeModify(TBoardVo tboardVo) {
		return boardDao.updateOne(tboardVo);
	}
}
