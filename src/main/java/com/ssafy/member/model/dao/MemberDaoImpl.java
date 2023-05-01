package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id) \n");
			sql.append("from members \n");
			sql.append("where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}
	
	@Override
	public MemberDto pwdFind(String userId, String email) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_pw \n");
			sql.append("from member \n");
			sql.append("where user_id = ? and user_email = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			memberDto = new MemberDto();
			memberDto.setUserPw("");
			if (rs.next()) {
				memberDto.setUserPw(rs.getString("user_pw"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public int joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member (user_id, user_pw, user_name, user_email, user_phone) \n");
			sql.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getUserId());
			pstmt.setString(2, memberDto.getUserPw());
			pstmt.setString(3, memberDto.getUserName());
			pstmt.setString(4, memberDto.getUserEmail());
			pstmt.setString(5, memberDto.getUserPhone());
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select user_id, user_name, user_email, user_phone\n");
			loginMember.append("from member \n");
			loginMember.append("where user_id = ? and user_pw = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserEmail(rs.getString("user_email"));
				memberDto.setUserPhone(rs.getString("user_phone"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from member where user_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void updateMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			int index = 0;
			if(memberDto.getUserPw().length() == 0) {
				sql.append("update member set user_email = ? where user_id = ?");
				pstmt = conn.prepareStatement(sql.toString());
				
			}else {
				sql.append("update member set user_pw = ?, user_email = ? where user_id = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(++index, memberDto.getUserPw());
			}
			
			pstmt.setString(++index, memberDto.getUserEmail());
			pstmt.setString(++index, memberDto.getUserId());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

}
