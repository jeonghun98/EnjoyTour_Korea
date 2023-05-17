package com.ssafy.notice.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.notice.model.NoticeDto;
import com.ssafy.notice.model.service.NoticeService;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/notice")
@Api(tags= {"공지사항 관리"})
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

//	@ApiOperation(value = "", notes = "공지사항을 <b>등록</b>합니다")
//	@ApiResponses({@ApiResponse(code = 200, message ="공지 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
////	@ApiImplicitParams({@ApiImplicitParam(name = "noticeDto", value ="등록 공지", required = true, dataType = "NoticeDto")})
//	@PostMapping
//	public ResponseEntity<?> noticeWrite(@RequestBody NoticeDto noticeDto) {
//		logger.debug("noticeWrite noticeDto : {}", noticeDto);
//		try {
//			noticeService.writeArticle(noticeDto);
//			
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pgno", "1");
//			map.put("key", "");
//			map.put("word", "");
//			List<NoticeDto> list = noticeService.listArticle(map);
//			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//	
	
	@ApiOperation(value = "공지사항 글작성", notes = "새로운 공지사항 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ApiResponses({@ApiResponse(code = 200, message ="공지 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PostMapping
	public ResponseEntity<?> noticeWrite(@RequestBody @ApiParam(value = "공지사항 정보.", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("noticeWrite - 호출");
		noticeService.writeArticle(noticeDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping
	public ResponseEntity<List<NoticeDto>> noticeList() throws Exception {
		logger.info("noticeList - 호출");
		Map<String, String> map = parameterCheck(null);
		return new ResponseEntity<List<NoticeDto>>(noticeService.listArticle(map), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeDto.class)
	@GetMapping("/{noticeno}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int noticeNo) throws Exception {
		logger.info("getArticle - 호출 : " + noticeNo);
		noticeService.updateHit(noticeNo);
		return new ResponseEntity<NoticeDto>(noticeService.getArticle(noticeNo), HttpStatus.OK);
	}
	
//	@ResponseBody
//	@ApiOperation(value = "", notes = "공지사항의 <b>전체 목록</b>을 리턴합니다.")
//	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
//	@GetMapping(value = "/")
//	public ResponseEntity<?> noticeList(@RequestParam Map<String, String> map) {
//		logger.debug("noticeList call");
//		try {
//			map = parameterCheck(map); // 임시 map
//			List<NoticeDto> list = noticeService.listArticle(map);
//			if(list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//		
//	}
	
//	@ApiOperation(value = "", notes = "공지사항의 <b>수정</b>합니다.")
//	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
//	@PutMapping
//	public ResponseEntity<?> noticeModify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map) {
//		logger.debug("noticeModify noticeDto : {}", noticeDto);
//		try {
//			noticeService.modifyArticle(noticeDto);
//			map = parameterCheck(map); // 임시 map
//			List<NoticeDto> list = noticeService.listArticle(map);
//			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	@ApiOperation(value = "공지사항 글수정", notes = "수정할 공지사항 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> noticeModify(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("noticeModify - 호출 {}", noticeDto);
		
		noticeService.modifyArticle(noticeDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

//	@ApiOperation(value = "", notes = "공지사항의 <b>삭제</b>합니다.")
//	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
//	@ApiImplicitParams({@ApiImplicitParam(name = "noticeno", value ="삭제 번호", required = true, dataType = "int", paramType = "path")})
//	@DeleteMapping(value = "/{noticeno}")
//	public ResponseEntity<?> noticeDelete(@PathVariable("noticeno") int noticeNo, @RequestParam Map<String, String> map) {
//		logger.debug("noticeDelete noticeDto : {}", noticeNo);
//		try {
//			noticeService.deleteArticle(noticeNo);
//			map = parameterCheck(map); // 임시 map
//			List<NoticeDto> list = noticeService.listArticle(map);
//			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
	@ApiOperation(value = "공지사항의 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{noticeno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("noticeno") @ApiParam(value = "살제할 글의 글번호.", required = true) int noticeNo) throws Exception {
		logger.info("deleteArticle - 호출");
		noticeService.deleteArticle(noticeNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		if (noticeService.deleteArticle(noticeNo)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	public Map<String, String> parameterCheck(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		if(map == null) {
			param.put("pgno", Integer.toString(ParameterCheck.notNumberToOne(null)));
			param.put("key", ParameterCheck.nullToBlank(null));
			param.put("word", ParameterCheck.nullToBlank(null));
		}else {
			param.put("pgno", Integer.toString(ParameterCheck.notNumberToOne(map.get("pgno"))));
			param.put("key", ParameterCheck.nullToBlank(map.get("key")));
			param.put("word", ParameterCheck.nullToBlank(map.get("word")));
		}
		return param;
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
