package com.ssafy.member.model.dao;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	MemberDto pwdFind(String userId, String email) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
}
