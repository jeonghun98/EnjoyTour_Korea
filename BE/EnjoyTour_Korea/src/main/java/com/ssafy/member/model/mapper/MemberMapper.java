package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userid) throws Exception; //아이디 체크
	MemberDto pwdFind(Map<String, String> map) throws Exception; //비밀번호 찾기
	
	int joinMember(MemberDto memberDto) throws Exception; //회원가입
	MemberDto loginMember(MemberDto memberDto) throws Exception; //로그인
	void deleteMember(String userid) throws Exception; //회원정보 삭제 
	void updateMember(MemberDto memberDto) throws Exception; //회원정보 수정
	
	MemberDto userInfo(String userid) throws Exception; // 회원 정보 호출
	// refresh token 관련
	void saveRefreshToken(Map<String, String> map) throws Exception; // 리프레쉬 토큰 저장
	Object getRefreshToken(String userid) throws Exception; // 리프레쉬 토큰 호출
	void deleteRefreshToken(Map<String, String> map) throws Exception; // 리프레쉬 토큰 삭제
	
}
