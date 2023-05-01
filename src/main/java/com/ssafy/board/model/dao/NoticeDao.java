package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.NoticeDto;

public interface NoticeDao {

	void writeArticle(NoticeDto boardDto) throws SQLException;
	List<NoticeDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	NoticeDto getArticle(int noticeNo) throws SQLException;
	void updateHit(int noticeNo) throws SQLException;
	
	void modifyArticle(NoticeDto boardDto) throws SQLException;
	void deleteArticle(int noticeNo) throws SQLException;
	
}
