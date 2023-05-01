package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.NoticeDto;
import com.ssafy.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao {
	
	private static NoticeDao noticeDao;
	private DBUtil dbUtil;
	
	private NoticeDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static NoticeDao getBoardDao() {
		if(noticeDao == null)
			noticeDao = new NoticeDaoImpl();
		return noticeDao;
	}

	@Override
	public void writeArticle(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into notice (user_id, notice_title, notice_content) \n");
			sql.append("values (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, noticeDto.getUserId());
			pstmt.setString(2, noticeDto.getTitle());
			pstmt.setString(3, noticeDto.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, Object> param) throws SQLException {
		List<NoticeDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select notice_no, user_id, notice_title, notice_content, notice_hit, notice_register_time \n");
			sql.append("from notice \n");
			String key = (String) param.get("key");
			String word = (String) param.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if("notice_title".equals(key)) {
					sql.append("where notice_title like concat('%', ?, '%') \n");
				} else {
					sql.append("where ").append(key).append(" = ? \n");
				}
			}
			sql.append("order by ").append(param.get("order")).append(" desc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!key.isEmpty() && !word.isEmpty())
				pstmt.setString(++idx, word);
			pstmt.setInt(++idx, (Integer) param.get("start"));
			pstmt.setInt(++idx, (Integer) param.get("listsize"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNoticeNo(rs.getInt("notice_no"));
				noticeDto.setUserId(rs.getString("user_id"));
				noticeDto.setTitle(rs.getString("notice_title"));
				noticeDto.setContent(rs.getString("notice_content"));
				noticeDto.setHit(rs.getInt("notice_hit"));
				noticeDto.setRegisterTime(rs.getString("notice_register_time"));
				
				list.add(noticeDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(notice_no) \n");
			sql.append("from notice \n");
			String key = (String) param.get("key");
			String word = (String) param.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if("notice_title".equals(key)) {
					sql.append("where notice_title like concat('%', ?, '%') \n");
				} else {
					sql.append("where ").append(key).append(" = ? \n");
				}
			}
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.isEmpty() && !word.isEmpty())
				pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public NoticeDto getArticle(int noticeNo) throws SQLException {
		NoticeDto noticeDao = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select notice_no, user_id, notice_title, notice_content, notice_hit, notice_register_time \n");
			sql.append("from notice \n");
			sql.append("where notice_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				noticeDao = new NoticeDto();
				noticeDao.setNoticeNo(rs.getInt("notice_no"));
				noticeDao.setUserId(rs.getString("user_id"));
				noticeDao.setTitle(rs.getString("notice_title"));
				noticeDao.setContent(rs.getString("notice_content"));
				noticeDao.setHit(rs.getInt("notice_hit"));
				noticeDao.setRegisterTime(rs.getString("notice_register_time"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return noticeDao;
	}

	@Override
	public void updateHit(int noticeNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update notice \n");
			sql.append("set notice_hit = notice_hit + 1 \n");
			sql.append("where notice_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}	
	}

	@Override
	public void modifyArticle(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update notice set notice_title = ?, notice_content = ? where notice_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, noticeDto.getTitle());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getNoticeNo());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void deleteArticle(int noticeNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from notice \n");
			sql.append("where notice_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}	
	}

}
