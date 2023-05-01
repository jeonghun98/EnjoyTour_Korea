package com.ssafy.attraction.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AttractionDto {
	private List<String> images = new ArrayList<>();
	private int contentid;
	private int contenttypeid;
	private String title;
	private String addr1;
	private String addr2;
	private String overview;
	private String zipcode;
	private String tel;
	private float latitude;
	private float longitude;
	private int sidoCode;
	private int gugunCode;
	private String areacode;
	private String booktour;
	private String cat1;
	private String cat2;
	private String cat3;
	private String cpyrhtDivCd;
	private String createdtime;
	private String dist;
	private String mlevel;
	private String modifiedtime;
	private String sigungucode;

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getBooktour() {
		return booktour;
	}

	public void setBooktour(String booktour) {
		this.booktour = booktour;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getCat3() {
		return cat3;
	}

	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}

	public String getCpyrhtDivCd() {
		return cpyrhtDivCd;
	}

	public void setCpyrhtDivCd(String cpythDivCd) {
		this.cpyrhtDivCd = cpythDivCd;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getMlevel() {
		return mlevel;
	}

	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}

	public String getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getSigungucode() {
		return sigungucode;
	}

	public void setSigungucode(String sigungucode) {
		this.sigungucode = sigungucode;
	}

	public AttractionDto() {
		images.add(null);
		images.add(null);
	}
	
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}

	@JsonGetter("contentid")
	public int getContentid() {
		return contentid;
	}

	@JsonSetter("contentid")
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	@JsonGetter("contenttypeid")
	public int getContenttypeid() {
		return contenttypeid;
	}
	@JsonSetter("contenttypeid")
	public void setContenttypeid(int contentypeid) {
		this.contenttypeid = contentypeid;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@JsonGetter("mapy")
	public float getLatitude() {
		return latitude;
	}
	@JsonSetter("mapy")
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@JsonGetter("mapx")
	public float getLongitude() {
		return longitude;
	}
	@JsonSetter("mapx")
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}


	@JsonGetter("firstimage")
	public String getFirstImage() {
		return images.get(0);
	}

	@JsonGetter("firstimage2")
	public String getFirstImage2() {
		return images.get(1);
	}
	@JsonSetter("firstimage")
	public void setFirstImage(String image) {
		this.images.set(0, image);
	}

	@JsonSetter("firstimage2")
	public void setFirstImage2(String image) {
		this.images.set(1, image);
	}
	@Override
	public String toString() {
		return "AttractionDto [images=" + images + ", contentId=" + ", contentTypeId="  
				+ ", title=" + title + ", addr1=" + addr1 + ", addr2=" + addr2 + ", overView=" + overview + ", zipcode="
				+ zipcode + ", tel=" + tel + ", mapY=" + latitude + ", mapX=" + longitude + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + "]";
	}
}
