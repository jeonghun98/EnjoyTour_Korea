package com.ssafy.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.notice.model.NoticeDto;
import com.ssafy.notice.model.NoticeParameterDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	void writeArticle(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	NoticeDto getArticle(int noticeNo) throws Exception;
	void updateHit(int noticeNo) throws Exception;
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	void deleteArticle(int noticeNo) throws Exception;
	
}
