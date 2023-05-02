package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	MemberDto pwdFind(Map<String, String> map) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(Map<String, String> map) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	
}
