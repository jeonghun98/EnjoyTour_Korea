package com.ssafy.plan.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.notice.model.NoticeDto;
import com.ssafy.plan.model.TripContentDto;
import com.ssafy.plan.model.TripPlanDto;
import com.ssafy.plan.model.service.TripPlanService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/tripplan")
public class TripPlanController{
	private static final Logger logger = LoggerFactory.getLogger(TripPlanController.class);
	
	private TripPlanService tripPlanService;

	@Autowired
	public TripPlanController(TripPlanService tripPlanService) {
		super();
		this.tripPlanService = tripPlanService;
	}
	
	@GetMapping("/view")
	public String view(Model model)
			throws Exception {
		return "plan/write";
	}
	
	@ResponseBody
	@PostMapping(value = "/write")
	public ResponseEntity<?> tripPlanWrite(@RequestBody List<String> map) {
		logger.debug("tripPlanWrite tripPlanDto : {}");
		try {
//			tripPlanService.writePlan(tripPlanDto);
//			tripPlanDto.setContentId(contents);
//			List<Integer> contents = tripPlanDto.getContentId();
			System.out.println(map.toString());
//			List<TripPlanDto> list = tripPlanService.listPlan();
			List<TripPlanDto> list = null;
			return new ResponseEntity<List<TripPlanDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
