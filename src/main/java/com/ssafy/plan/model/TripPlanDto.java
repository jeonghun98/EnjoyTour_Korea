package com.ssafy.plan.model;

import java.util.ArrayList;
import java.util.List;

public class TripPlanDto {
	public class TripList {
		private String[] attractionList;

		public String[] getAttractionList() {
			return attractionList;
		}

		public void setAttractionList(String[] attractionList) {
			this.attractionList = attractionList;
		}

		@Override
		public String toString() {
			return "TripList [attractionList=" + attractionList + "]";
		}
	}

	private String userId;
	private TripList tripList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TripList getTripList() {
		return tripList;
	}

	public void setTripList(TripList tripList) {
		this.tripList = tripList;
	}

	@Override
	public String toString() {
		return "TripPlanDto [userId=" + userId + ", tripList=" + tripList + "]";
	}
	
}
