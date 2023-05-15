package com.ssafy.plan.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plan.model.TripPlanDto;
import com.ssafy.plan.model.mapper.TripPlanMapper;

@Service
public class TripPlanServiceImpl implements TripPlanService {
	
	private TripPlanMapper tripPlanMapper;
	
	public TripPlanServiceImpl(TripPlanMapper tripPlanMapper) {
		super();
		this.tripPlanMapper = tripPlanMapper;
	}

	@Override
	@Transactional
	public void writePlan(TripPlanDto tripPlanDto) throws Exception {
		tripPlanMapper.writePlan(tripPlanDto);

	}

	@Override
	public List<TripPlanDto> listPlan() throws Exception {
		return tripPlanMapper.listPlan();
	}

	@Override
	public TripPlanDto getPlan(int planNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyPlan(TripPlanDto planNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePlan(int planNo) throws Exception {
		// TODO Auto-generated method stub

	}

}
