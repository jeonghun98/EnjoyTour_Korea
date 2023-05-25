package com.ssafy.hotplace.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HotplaceDto (핫플레이스 정보)", description = "핫플레이스 번호, 사용자, 제목, 내용, 좋아요 수, 다녀온 날짜, 이미지, 파일 정보를 가진 Domain class!!")
public class HotplaceDto {
	
	@ApiModelProperty(value = "핫플레이스 번호")
	private int hotplaceNo;
	@ApiModelProperty(value = "사용자")
	private String userId;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "좋아요 수")
	private int like;
	@ApiModelProperty(value = "다녀온 날짜")
	private String date;
	@ApiModelProperty(value = "파일 정보")
	private List<FileInfoDto> fileInfos;
	
	private float latitude;
	private float longitude;
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public int getHotplaceNo() {
		return hotplaceNo;
	}
	public void setHotplaceNo(int hotplaceNo) {
		this.hotplaceNo = hotplaceNo;
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
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}
	@Override
	public String toString() {
		return "HotplaceDto [hotplaceNo=" + hotplaceNo + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", like=" + like + ", date=" + date + ", fileInfos=" + fileInfos + "]";
	}
	
	
	
}
