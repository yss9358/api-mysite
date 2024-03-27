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
	
}
