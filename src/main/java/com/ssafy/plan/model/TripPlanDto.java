package com.ssafy.plan.model;

import java.util.ArrayList;
import java.util.List;

public class TripPlanDto {
	private int planNo;
	private String title;
	private String content;
	private int grade;
	private String startDate;
	private String endDate;
	private List<Integer> contentId;
	private boolean planPulic;
	
	public TripPlanDto() {
		contentId = new ArrayList<Integer>();
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
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

	public List<Integer> getContentId() {
		return contentId;
	}

	public void setContentId(List<Integer> contentId) {
		for(Integer i : contentId) {
			contentId.add(i);
		}
	}

	public boolean isPlanPulic() {
		return planPulic;
	}

	public void setPlanPulic(boolean planPulic) {
		this.planPulic = planPulic;
	}

	@Override
	public String toString() {
		return "TripPlanDto [planNo=" + planNo + ", title=" + title + ", content=" + content + ", grade=" + grade
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", contentId=" + contentId.toString() + ", planPulic="
				+ planPulic + "]";
	}

	
}
