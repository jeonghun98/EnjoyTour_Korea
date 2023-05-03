package com.ssafy.hotplace.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.hotplace.model.HotplaceDto;
import com.ssafy.hotplace.model.service.HotplaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/hotplace")
@Api(tags= {"핫플레이스 관리"})
public class HotplaceController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotplaceController.class);
	
	private HotplaceService hotplaceService;

	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}

	@ResponseBody
	@ApiOperation(value = "", notes = "핫플레이스를 <b>등록</b>합니다")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PostMapping(value="/")
	public ResponseEntity<?> hotplaceWrite(@RequestBody HotplaceDto hotplaceDto){
		logger.debug("hotplaceWrite hotplaceDto: "+hotplaceDto.toString());
		try {
			hotplaceService.writeHotplace(hotplaceDto);
			List<HotplaceDto> list = hotplaceService.listHotplace();
			return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "핫플레이스의 <b>전체 목록</b>을 리턴합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping(value = "/")
	public ResponseEntity<?> hotplaceList() {
		logger.debug("hotplaceList call");
		try {
			List<HotplaceDto> list = hotplaceService.listHotplace();
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping("/view")
	public String view(@RequestParam("hotplaceno") int hotplaceNo, Model model)
			throws Exception {
		HotplaceDto hotplaceDto = hotplaceService.getHotplace(hotplaceNo);
		model.addAttribute("hotplace", hotplaceDto);
		return "hotplace/view";
	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "핫플레이스를 <b>수정</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PutMapping(value = "/")
	public ResponseEntity<?> hotplaceModify(@RequestBody HotplaceDto hotplaceDto) {
		logger.debug("hotplaceModify hotplaceDto : {}", hotplaceDto);
		try {
			hotplaceService.modifyHotplace(hotplaceDto);
			List<HotplaceDto> list = hotplaceService.listHotplace();
			return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "핫플레이스를 <b>삭제</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="핫플 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@ApiImplicitParams({@ApiImplicitParam(name = "hotplaceno", value ="삭제 번호", required = true, dataType = "int", paramType = "path")})
	@DeleteMapping(value = "/{hotplaceno}")
	public ResponseEntity<?> hotplaceDelete(@PathVariable("hotplaceno") int hotplaceNo) {
		logger.debug("hotplaceDelete hotplaceDto : {}", hotplaceNo);
		try {
			hotplaceService.deleteHotplace(hotplaceNo);
			List<HotplaceDto> list = hotplaceService.listHotplace();
			return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
