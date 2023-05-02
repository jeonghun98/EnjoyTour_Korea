package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

@Controller
@RequestMapping("/user")
public class MemberController {
//	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
       
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@GetMapping("/{userid}")
	@ResponseBody
	public int idCheck(@PathVariable("userid") String userId) throws Exception {
		// (0 : 사용 X, 1 : 사용 O)
		System.out.println("MemberController - idCheck 함수");
		return memberService.idCheck(userId);
	}
	
	@GetMapping("/pwdfind")
	@ResponseBody
	public String pwdfind(@RequestParam(name = "userid") String userId, @RequestParam(name = "email") String email) throws Exception {
		System.out.println("pwdfind함수 파라미터 "+userId+" "+email);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userEmail", email);
		MemberDto memberDto = memberService.pwdFind(map);
		System.out.println("MemberController - pwdfind 함수"+memberDto.toString());
		String pwd = memberDto.getUserPw();
		System.out.println("pwd:"+pwd);
		return pwd;
	}
	
//	@GetMapping("/join")
//	public String join() {
//		return "redirect:/user/modal";
//	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto) throws Exception{
		System.out.println("join 파라미터"+memberDto.toString());
//		MemberDto memberDto2 = new MemberDto();
//		memberDto2.setUserName(memberDto.getUserName());
//		memberDto2.setUserId(memberDto.getUserId());
//		memberDto2.setUserPw(memberDto.getUserPw());
//		memberDto2.setUserEmail(memberDto.getUserEmail());
//		memberDto2.setUserPhone(memberDto.getUserPhone());
//		System.out.println("join 결과값"+memberDto2.toString());
		int cnt = memberService.joinMember(memberDto);
		System.out.println("MemberController - join 함수 "+cnt);
		return "redirect:/";
	}
	
//	@GetMapping("/login")
//	public String login() {
//		return "redirect:/user/modal";
//	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "login_id") String userId, @RequestParam(name = "login_pwd") String userPwd,
			@RequestParam(name = "saveid", required = false) String idsave,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("PostMapping login 파라미터 "+userId+" "+userPwd);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPwd);		
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				System.out.println("MemberController - login 함수"+memberDto.toString());
				if("ok".equals(idsave)) {
					Cookie cookie = new Cookie("user_id", userId);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
					response.addCookie(cookie);
				} else {
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("user_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "redirect:/";
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 에러 발생!!!");
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam(name = "mypage_pwd") String pwd, @RequestParam(name = "mypage_email") String email,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		
		if (memberDto != null) {
			String userId = memberDto.getUserId();
			String userPhone = memberDto.getUserPhone();
			String userName = memberDto.getUserName();
			String useremail = memberDto.getUserEmail();
			String userPw = memberDto.getUserPw();
			
			MemberDto memberDto_set = new MemberDto();
			memberDto_set.setUserPw(userPw);
			if(pwd.length() != 0)
				memberDto_set.setUserPw(pwd);
			
			memberDto_set.setUserEmail(useremail);
			if(email.length() != 0)
				memberDto_set.setUserEmail(email);

			memberDto_set.setUserId(userId);
			
			try {
				memberService.updateMember(memberDto_set);
				memberDto_set.setUserPhone(userPhone);
				memberDto_set.setUserName(userName);
				memberDto_set.setUserPw("");
				
				session.setAttribute("userinfo", memberDto_set); // session userinfo update
				
				return "redirect:/";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "유저 정보 업데이트 중 에러 발생!!!");
				return "redirect:/";
			}
		} else
			return "redirect:/index";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			String userId = memberDto.getUserId();
			
				try {
					memberService.deleteMember(userId);
					session.invalidate();
					return "redirect:/";
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/";
				}
			} else
				return "redirect:/";
		}
	
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action");
//		
//		String path = "";
//		if("mvjoin".equals(action)) {
//			path = "/user/join.jsp";
//			redirect(request, response, path);
//		} else if("idcheck".equals(action)) { // 0 or 1
//			int cnt = idcheck(request, response);
//			response.setContentType("text/plain");
//			PrintWriter out = response.getWriter();
//			out.print(cnt);
//		} else if("pwdfind".equals(action)) {
//			String pwd = pwdfind(request, response);
//			response.setContentType("text/plain");
//			PrintWriter out = response.getWriter();
//			out.print(pwd);
//		} else if("join".equals(action)) {
//			path = join(request, response);
//			redirect(request, response, path);
//		} else if("mvlogin".equals(action)) {
//			path = "/user/login.jsp";
//			redirect(request, response, path);
//		} else if("login".equals(action)) {
//			path = login(request, response);
//			redirect(request, response, path);
//		} else if("logout".equals(action)) {
//			path = logout(request, response);
//			redirect(request, response, path);
//		} else if("update".equals(action)) {
//			path = update(request, response);
//			redirect(request, response, path);
//		} else if("delete".equals(action)) {
//			path = delete(request, response);
//			redirect(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//	
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//	
//	private int idcheck(HttpServletRequest request, HttpServletResponse response) {
//		// (0 : 사용 X, 1 : 사용 O)
//		String userId = request.getParameter("userid");
//		try {
//			return memberService.idCheck(userId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 500;
//		}
//	}
//	
//	private String pwdfind(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//		String email = request.getParameter("email");
//		// 위에 두개 map으로 해서 map을 넣어주기??
//		/*
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userId", request.getParameter("userid"));
//		map.put("email", request.getParameter("email"));
//		MemberDto memberDto = memberService.pwdFind(map);
//		*/
//		try {
//			MemberDto memberDto = memberService.pwdFind(userId, email);
//			return memberDto.getUserPwd();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//	
//	private String join(HttpServletRequest request, HttpServletResponse response) {
//		MemberDto memberDto = new MemberDto();
//		memberDto.setUserName(request.getParameter("join_name"));
//		memberDto.setUserId(request.getParameter("join_id"));
//		memberDto.setUserPwd(request.getParameter("join_pwd"));
//		memberDto.setEmail(request.getParameter("join_email"));
//		memberDto.setAge(Integer.parseInt(request.getParameter("join_age")));
//		try {
//			int cnt = memberService.joinMember(memberDto);
//			return "/index.jsp";
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//	
//	private String login(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("login_id");
//		String userPwd = request.getParameter("login_pwd");
//		/*
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userId", request.getParameter("login_id"));
//		map.put("userPwd", request.getParameter("login_pwd"));
//		*/
//		try {
//			MemberDto memberDto = memberService.loginMember(userId, userPwd);
//			if(memberDto != null) {
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", memberDto);
//				
//				String idsave = request.getParameter("saveid");
//				if("ok".equals(idsave)) {
//					Cookie cookie = new Cookie("user_id", userId);
//					cookie.setPath(request.getContextPath());
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//					response.addCookie(cookie);
//				} else {
//					Cookie cookies[] = request.getCookies();
//					if(cookies != null) {
//						for(Cookie cookie : cookies) {
//							if("user_id".equals(cookie.getName())) {
//								cookie.setMaxAge(0);
//								response.addCookie(cookie);
//								break;
//							}
//						}
//					}
//				}
//				return "/index.jsp";
//			} else {
//				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
//				return "/index.jsp";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "로그인 중 에러 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//	
//	private String logout(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		session.invalidate();
//		return "/index.jsp";
//	}
//	
//	private String update(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			String userId = memberDto.getUserId();
//			int age = memberDto.getAge();
//			String userName = memberDto.getUserName();
//			String email = memberDto.getEmail();
//			
//			MemberDto memberDto_set = new MemberDto();
//			memberDto_set.setUserPwd(request.getParameter("mypage_pwd"));
//			
//			memberDto_set.setEmail(email);
//			if(request.getParameter("mypage_email").length() != 0)
//				memberDto_set.setEmail(request.getParameter("mypage_email"));
//			
//			memberDto_set.setUserId(userId);
//			
//		try {
//			memberService.updateMember(memberDto_set);
//			memberDto_set.setAge(age);
//			memberDto_set.setUserName(userName);
//			memberDto_set.setUserPwd("");
//			
//			session.setAttribute("userinfo", memberDto_set); // session userinfo update
//			
//			return "/index.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "유저 정보 업데이트 중 에러 발생!!!");
//			return "/error/error.jsp";
//		}
//	} else
//		return "/index.jsp";
//	}

//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//		String userId = memberDto.getUserId();
//			try {
//				memberService.deleteMember(userId);
//				session.invalidate();
//				return "/index.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				return "/error/error.jsp";
//			}
//		} else
//			return "/index.jsp";
//	
//		}
//	
//	}

