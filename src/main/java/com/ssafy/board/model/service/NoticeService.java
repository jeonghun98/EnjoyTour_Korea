package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.NoticeDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	void writeArticle(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	NoticeDto getArticle(int noticeNo) throws Exception;
	void updateHit(int noticeNo) throws Exception;
	
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	void deleteArticle(int noticeNo) throws Exception;
	
}
