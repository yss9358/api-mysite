package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 전체 리스트 가져오기
	public List<GuestbookVo> list() {
		return sqlSession.selectList("guestbook.list");
	}
	
	// 등록
	public int insertGuest(GuestbookVo guestbookVo) {
		return sqlSession.insert("guestbook.insertGuest", guestbookVo);
	}
	
	// 한명 데이터 가져오기
	public GuestbookVo selectOneByNo(int no) {
		return sqlSession.selectOne("guestbook.selectOneByNo", no);
	}
	
}
