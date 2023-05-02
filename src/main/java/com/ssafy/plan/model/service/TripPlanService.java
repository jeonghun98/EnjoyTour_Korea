package com.ssafy.plan.model.service;

import java.util.List;
import com.ssafy.plan.model.TripPlanDto;

public interface TripPlanService {
	void writePlan(TripPlanDto tripPlanDto) throws Exception;
	List<TripPlanDto> listPlan() throws Exception;
	TripPlanDto getPlan(int planNo) throws Exception;
	void modifyPlan(TripPlanDto planNo) throws Exception;
	void deletePlan(int planNo) throws Exception;
}
