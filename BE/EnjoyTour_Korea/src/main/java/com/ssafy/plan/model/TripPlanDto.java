package com.ssafy.plan.model;

import java.util.ArrayList;
import java.util.List;

public class TripPlanDto {
	private int planNo;
	private String userId;
	private String title;
	private String content;
	private int grade;
	private String startDate;
	private String endDate;
	private List<Integer> contentIds;
	private boolean planPublic;
	
	public TripPlanDto() {
		contentIds = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return "TripPlanDto [planNo=" + planNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", grade=" + grade + ", startDate=" + startDate + ", endDate=" + endDate + ", contentIds="
				+ contentIds + ", planPublic=" + planPublic + "]";
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getContentIds() {
		return contentIds;
	}

	public void setContentIds(List<Integer> contentIds) {
		this.contentIds = contentIds;
	}

	public boolean isPlanPublic() {
		return planPublic;
	}

	public void setPlanPublic(boolean planPublic) {
		this.planPublic = planPublic;
	}
	
	
}
