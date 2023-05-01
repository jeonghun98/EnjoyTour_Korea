package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.NoticeDto;
import com.ssafy.board.model.dao.NoticeDao;
import com.ssafy.board.model.dao.NoticeDaoImpl;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

public class NoticeServiceImpl implements NoticeService {
	
	private static NoticeService boardService = new NoticeServiceImpl();
	private NoticeDao noticeDao;
	
	private NoticeServiceImpl() {
		noticeDao = NoticeDaoImpl.getBoardDao();
	}

	public static NoticeService getBoardService() {
		return boardService;
	}

	@Override
	public void writeArticle(NoticeDto boardDto) throws Exception {
		noticeDao.writeArticle(boardDto);
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		return noticeDao.listArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = noticeDao.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int noticeNo) throws Exception {
		return noticeDao.getArticle(noticeNo);
	}

	@Override
	public void updateHit(int noticeNo) throws Exception {
		noticeDao.updateHit(noticeNo);
	}

	@Override
	public void modifyArticle(NoticeDto boardDto) throws Exception {
		noticeDao.modifyArticle(boardDto);
		
	}

	@Override
	public void deleteArticle(int noticeNo) throws Exception {
		noticeDao.deleteArticle(noticeNo);
	}

}
