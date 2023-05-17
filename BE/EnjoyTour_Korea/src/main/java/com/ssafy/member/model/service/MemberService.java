package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception; //아이디 체크
	MemberDto pwdFind(Map<String, String> map) throws Exception; //비밀번호 찾기
	
	int joinMember(MemberDto memberDto) throws Exception; //회원가입
	MemberDto loginMember(MemberDto memberDto) throws Exception; //로그인
	void deleteMember(String userId) throws Exception; //회원정보 삭제 
	void updateMember(MemberDto memberDto) throws Exception; //회원정보 수정
	
	MemberDto userInfo(String userId) throws Exception; // 회원 정보 호출
	// refresh token 관련
	void saveRefreshToken(String userId, String refreshToken) throws Exception; // 리프레쉬 토큰 저장
	Object getRefreshToken(String userId) throws Exception; // 리프레쉬 토큰 호출
	void deleteRefreshToken(String userId) throws Exception; // 리프레쉬 토큰 삭제
}
