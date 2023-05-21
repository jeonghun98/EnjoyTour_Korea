package com.ssafy.attraction.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AttractionDto {
//	private List<String> images = new ArrayList<>();
	private String image1;
	private String image2;
	private int contentid;
	private int contenttypeid;
	private String title;
	private String addr1;
	private String addr2;
	private String overview;
	
//	private String zipcode;
//	private String tel;
	
	private float latitude;
	private float longitude;
	private String tel;
	//	private int sidoCode;
//	private int gugunCode;
//	private String areacode;
//	private String booktour;
//	private String cat1;
//	private String cat2;
//	private String cat3;
//	private String cpyrhtDivCd;
//	private String createdtime;
//	private String dist;
//	private String mlevel;
//	private String modifiedtime;
//	private String sigungucode;
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public int getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "AttractionDto [image1=" + image1 + ", image2=" + image2 + ", contentid=" + contentid
				+ ", contenttypeid=" + contenttypeid + ", title=" + title + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", overview=" + overview + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
