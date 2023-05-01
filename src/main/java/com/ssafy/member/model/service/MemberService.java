package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	MemberDto pwdFind(String userId, String email) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(String userId, String userPwd) throws Exception;
	void deleteMember(String userId) throws Exception;
	void updateMember(MemberDto memberDto) throws Exception;
}
