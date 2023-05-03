package com.ssafy.plan.model;

import java.util.List;

public class TripContentDto {
	private int planNo;
	private int contentId;
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	@Override
	public String toString() {
		return "ContentDto [planNo=" + planNo + ", contentId=" + contentId + "]";
	}
	
}
