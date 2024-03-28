package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 리스트 가져오기
	public List<AttachVo> list() {
		return sqlSession.selectList("gallery.list");
	}
	
	// 등록
	public int insertImage(AttachVo attachVo) {
		return sqlSession.insert("gallery.insertImage", attachVo);
	}
	
	// 삭제
	public int deleteByNo(int no) {
		return sqlSession.delete("gallery.deleteByNo", no);
	}
	
	// 한명 데이터 가져오기
	public AttachVo selectOneData(int no) {
		return sqlSession.selectOne("gallery.selectOneData",no);
	}
	

}
