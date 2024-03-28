package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TBoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 리스트 가져오기
	public List<TBoardVo> list() {
		return sqlSession.selectList("board.list");
	}
	
	// 추가 리스트 가져오기
	public List<TBoardVo> moreList(int no) {
		return sqlSession.selectList("board.moreList",no);
	}
	
	// 한명 데이터 가져오기
	public TBoardVo selectOneByNo(int no) {
		return sqlSession.selectOne("board.selectOneByNo", no);
	}
	
	// 등록
	public int insertBoard(TBoardVo tBoardVo) {
		return sqlSession.insert("board.insertBoard",tBoardVo);
	}
	
	// 삭제
	public int deleteByNo(int no) {
		return sqlSession.delete("board.deleteByNo", no);
	}
	
	// 조회수 올리기
	public int hitUpdateByNo(int no) {
		return sqlSession.update("board.hitUpdateByNo", no);
	}
	
	// 수정
	public int updateOne(TBoardVo tboardVo) {
		return sqlSession.update("board.updateOne",tboardVo);
	}
}
