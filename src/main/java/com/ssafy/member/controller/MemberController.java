//package com.ssafy.member.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.member.model.service.MemberService;
//import com.ssafy.member.model.service.MemberServiceImpl;
//
//@WebServlet("/user")
//public class MemberController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private MemberService memberService;
//	
//	public void init() {
//		memberService = MemberServiceImpl.getMemberService();
//	}
//       
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
//		MemberDto memberDto = memberService.pwdFind(map);
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
//
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
//	}
//
