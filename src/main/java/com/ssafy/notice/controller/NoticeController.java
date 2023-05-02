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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/notice")
@Api(tags= {"공지사항 관리"})
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

//	@GetMapping("/write")
//	public String write(@RequestParam Map<String, String> map, Model model) {
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "notice/write";
//	}
//
//	@PostMapping("/write")
//	public String write(NoticeDto noticeDto, HttpSession session,
//			RedirectAttributes redirectAttributes) throws Exception {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		noticeDto.setUserId(memberDto.getUserId());
//		noticeService.writeArticle(noticeDto);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
//		return "redirect:/notice/list";
//	}

	@ResponseBody
	@ApiOperation(value = "", notes = "공지사항을 <b>등록</b>합니다")
	@ApiResponses({@ApiResponse(code = 200, message ="공지 등록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
//	@ApiImplicitParams({@ApiImplicitParam(name = "noticeDto", value ="등록 공지", required = true, dataType = "NoticeDto")})
	@PostMapping(value = "/")
	public ResponseEntity<?> noticeWrite(@RequestBody NoticeDto noticeDto) {
		logger.debug("noticeWrite noticeDto : {}", noticeDto);
		try {
			noticeService.writeArticle(noticeDto);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			map.put("key", "");
			map.put("word", "");
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	@GetMapping("/list")
//	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		List<NoticeDto> list = noticeService.listArticle(map);
//		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
//		mav.addObject("notices", list);
//		mav.addObject("navigation", pageNavigation);
//		mav.addObject("pgno", map.get("pgno"));
//		mav.addObject("key", map.get("key"));
//		mav.addObject("word", map.get("word"));
//		mav.setViewName("board/list");
//		return mav;
//	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "공지사항의 <b>전체 목록</b>을 리턴합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@GetMapping(value = "/")
	public ResponseEntity<?> noticeList(@RequestParam Map<String, String> map) {
		logger.debug("noticeList call");
		try {
			map = parameterCheck(map); // 임시 map
			List<NoticeDto> list = noticeService.listArticle(map);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping("/view")
	public String view(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		NoticeDto noticeDto = noticeService.getArticle(noticeNo);
		noticeService.updateHit(noticeNo);
		model.addAttribute("notice", noticeDto);
		Map<String, String> param = parameterCheck(map);
		model.addAttribute("pgno", param.get("pgno"));
		model.addAttribute("key", param.get("key"));
		model.addAttribute("word", param.get("word"));
		return "notice/view";
	}

	
//	@GetMapping("/modify")
//	public String modify(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		NoticeDto noticeDto = noticeService.getArticle(noticeNo);
//		model.addAttribute("article", noticeDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "notice/modify";
//	}
//
//	@PostMapping("/modify")
//	public String modify(NoticeDto noticeDto, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		noticeService.modifyArticle(noticeDto);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/notice/list";
//	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "공지사항의 <b>수정</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@PutMapping(value = "/")
	public ResponseEntity<?> noticeModify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map) {
		logger.debug("noticeModify noticeDto : {}", noticeDto);
		try {
			noticeService.modifyArticle(noticeDto);
			map = parameterCheck(map); // 임시 map
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	@GetMapping("/delete")
//	public String delete(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		noticeService.deleteArticle(noticeNo);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/notice/list";
//	}
	
	@ResponseBody
	@ApiOperation(value = "", notes = "공지사항의 <b>삭제</b>합니다.")
	@ApiResponses({@ApiResponse(code = 200, message ="공지 전체 목록 OK"), @ApiResponse(code = 500, message ="서버 에러")})
	@ApiImplicitParams({@ApiImplicitParam(name = "noticeno", value ="삭제 번호", required = true, dataType = "int", paramType = "path")})
	@DeleteMapping(value = "/{noticeno}")
	public ResponseEntity<?> noticeDelete(@PathVariable("noticeno") int noticeNo, @RequestParam Map<String, String> map) {
		logger.debug("noticeDelete noticeDto : {}", noticeNo);
		try {
			noticeService.deleteArticle(noticeNo);
			map = parameterCheck(map); // 임시 map
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	public Map<String, String> parameterCheck(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pgno", Integer.toString(ParameterCheck.notNumberToOne(map.get("pgno"))));
		param.put("key", ParameterCheck.nullToBlank(map.get("key")));
		param.put("word", ParameterCheck.nullToBlank(map.get("word")));
		return param;
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
