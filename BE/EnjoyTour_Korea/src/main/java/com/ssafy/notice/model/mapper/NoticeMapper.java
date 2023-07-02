package com.ssafy.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.notice.model.NoticeDto;
import com.ssafy.notice.model.NoticeParameterDto;

@Mapper
public interface NoticeMapper {
	void writeArticle(NoticeDto boardDto) throws SQLException;
	List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	NoticeDto getArticle(int noticeNo) throws SQLException;
	void updateHit(int noticeNo) throws SQLException;
	void modifyArticle(NoticeDto boardDto) throws SQLException;
	void deleteArticle(int noticeNo) throws SQLException;
}
