package com.ssafy.notice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NoticeDto (공지사항 정보)", description = "공지사항 번호, 관리자아이디, 제목, 내용, 조회수, 기입일 정보를 가진 Domain class!!")
public class NoticeDto {

	@ApiModelProperty(value = "공지사항 번호")
	private int noticeNo;
	@ApiModelProperty(value = "관리자아이디")
	private String userId;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "기입일 정보")
	private String registerTime;
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", registerTime=" + registerTime + "]";
	}
}
