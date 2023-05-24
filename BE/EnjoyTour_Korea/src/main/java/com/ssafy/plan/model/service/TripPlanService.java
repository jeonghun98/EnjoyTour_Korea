package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.notice.model.NoticeParameterDto;
import com.ssafy.plan.model.TripPlanDto;

public interface TripPlanService {
	void writePlan(TripPlanDto tripPlanDto) throws Exception;
	List<TripPlanDto> listPlan(NoticeParameterDto noticeParameterDto) throws Exception;
	List<TripPlanDto> mylistPlan(String userId) throws Exception;
	List<AttractionDto> getPlan(int planNo) throws Exception;
	void modifyPlan(TripPlanDto planNo) throws Exception;
	void deletePlan(int planNo) throws Exception;
}
