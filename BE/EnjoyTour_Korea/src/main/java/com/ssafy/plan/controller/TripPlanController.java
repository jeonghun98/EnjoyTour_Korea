package com.ssafy.plan.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.notice.model.NoticeDto;
import com.ssafy.notice.model.NoticeParameterDto;
import com.ssafy.plan.model.TripPlanDto;
import com.ssafy.plan.model.service.TripPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/plan")
@Api(tags= {"plan 관리"})
public class TripPlanController{
	private static final Logger logger = LoggerFactory.getLogger(TripPlanController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private TripPlanService tripPlanService;

	@Autowired
	public TripPlanController(TripPlanService tripPlanService) {
		super();
		this.tripPlanService = tripPlanService;
	}
	
	@ApiOperation(value = "plan 글작성", notes = "새로운 plan 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ApiResponses({@ApiResponse(code = 200, message ="공지 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PostMapping
	public ResponseEntity<?> tripPlanWrite(
			@RequestBody TripPlanDto tripPlanDto
			) throws Exception {

		logger.debug("tripPlanWrite tripPlanDto : {}", tripPlanDto);
		tripPlanService.writePlan(tripPlanDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "myplan 글목록", notes = "나의 plan 정보를 반환한다.", response = List.class)
	@ApiResponses({@ApiResponse(code = 200, message ="plan 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping("/myplan/{userId}")
	public ResponseEntity<List<TripPlanDto>> mytripPlanList(
			@PathVariable("userId") @ApiParam(value = "userId.", required = true) String userId) throws Exception {
		logger.info("tripPlanList - 호출");
		return new ResponseEntity<List<TripPlanDto>>(tripPlanService.mylistPlan(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "plan 글목록", notes = "모든 plan 정보를 반환한다.", response = List.class)
	@ApiResponses({@ApiResponse(code = 200, message ="plan 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping
	public ResponseEntity<List<TripPlanDto>> tripPlanList(@ApiParam(value = "plan 얻기위한 부가정보.", required = false) NoticeParameterDto noticeParameterDto) throws Exception {
		logger.info("tripPlanList - 호출");
		return new ResponseEntity<List<TripPlanDto>>(tripPlanService.listPlan(noticeParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 plan 정보", notes = "plan 정보를 반환한다.")
	@ApiResponses({@ApiResponse(code = 200, message ="plan 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping("/{planNo}")
	public ResponseEntity<List<AttractionDto>> searchPlan(
			@PathVariable("planNo") @ApiParam(value = "planNo", required = true) int planNo
			) throws Exception {
		logger.debug("searchPlan planNo : ", planNo);
		return new ResponseEntity<List<AttractionDto>>(tripPlanService.getPlan(planNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "plan 글삭제", notes = "글번호에 해당하는 plan의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{planNo}")
	public ResponseEntity<String> deletePlan(@PathVariable("planNo") @ApiParam(value = "살제할 글의 글번호.", required = true) int planNo) throws Exception {
		logger.info("deletePlan - 호출");
		tripPlanService.deletePlan(planNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
