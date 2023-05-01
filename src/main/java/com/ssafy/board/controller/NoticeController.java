package com.ssafy.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.NoticeDto;
import com.ssafy.board.model.service.NoticeService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/notice")
public class NoticeController{
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServletContext servletContext;

	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "notice/write";
	}

	@PostMapping("/write")
	public String write(NoticeDto noticeDto, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		noticeDto.setUserId(memberDto.getUserId());
		noticeService.writeArticle(noticeDto);
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		return "redirect:/notice/list";
	}

	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<NoticeDto> list = noticeService.listArticle(map);
		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
		mav.addObject("notices", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("board/list");
		return mav;
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

	@GetMapping("/modify")
	public String modify(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		NoticeDto noticeDto = noticeService.getArticle(noticeNo);
		model.addAttribute("article", noticeDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "notice/modify";
	}

	@PostMapping("/modify")
	public String modify(NoticeDto noticeDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		noticeService.modifyArticle(noticeDto);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/notice/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("noticeno") int noticeNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		noticeService.deleteArticle(noticeNo);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/notice/list";
	}
	
	public Map<String, String> parameterCheck(Map<String, String> map) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("pgno", Integer.toString(ParameterCheck.notNumberToOne(map.get("pgno"))));
		param.put("key", ParameterCheck.nullToBlank(map.get("key")));
		param.put("word", ParameterCheck.nullToBlank(map.get("word")));
		return param;
	}
}
