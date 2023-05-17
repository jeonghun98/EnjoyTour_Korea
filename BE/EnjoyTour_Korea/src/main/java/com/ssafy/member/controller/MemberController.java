//package com.ssafy.member.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.member.model.service.JwtServiceImpl;
//import com.ssafy.member.model.service.MemberService;
//import com.ssafy.member.model.service.MemberServiceImpl;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
//@RestController
////@Controller
//@RequestMapping("/user")
//@Api("회원 컨트롤러 REST API")
//public class MemberController {
////	private static final long serialVersionUID = 1L;
//	
//	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//	
//	@Autowired
//	private JwtServiceImpl jwtService;
//	
//	private MemberService memberService;
//    
//	@Autowired
//	public MemberController(MemberService memberService) {
//		super();
//		this.memberService = memberService;
//	}
//	
//	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			MemberDto loginUser = memberService.loginMember(memberDto);
//			if (loginUser != null) {
//				String accessToken = jwtService.createAccessToken("userId", loginUser.getUserId());// key, data
//				String refreshToken = jwtService.createRefreshToken("userId", loginUser.getUserId());// key, data
//				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
//				logger.debug("로그인 accessToken 정보 : {}", accessToken);
//				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
//	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
//	@GetMapping("/info/{userid}")
//	public ResponseEntity<Map<String, Object>> getInfo(
//			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
//			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.UNAUTHORIZED;
//		if (jwtService.checkToken(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			try {
////				로그인 사용자 정보.
//				MemberDto memberDto = memberService.userInfo(userid);
//				resultMap.put("userInfo", memberDto);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
//				resultMap.put("message", e.getMessage());
//				status = HttpStatus.INTERNAL_SERVER_ERROR;
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.UNAUTHORIZED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
//	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
//	@GetMapping("/logout/{userid}")
//	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		try {
//			memberService.deleteRefreshToken(userid);
//			resultMap.put("message", SUCCESS);
//			status = HttpStatus.ACCEPTED;
//		} catch (Exception e) {
//			logger.error("로그아웃 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//
//	}
//	
//	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
//	@PostMapping("/refresh")
//	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		String token = request.getHeader("refresh-token");
//		logger.debug("token : {}, memberDto : {}", token, memberDto);
//		if (jwtService.checkToken(token)) {
//			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
//				String accessToken = jwtService.createAccessToken("userid", memberDto.getUserId());
//				logger.debug("token : {}", accessToken);
//				logger.debug("정상적으로 액세스토큰 재발급!!!");
//				resultMap.put("access-token", accessToken);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			}
//		} else {
//			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
////	@GetMapping("/{userid}")
////	@ResponseBody
////	public int idCheck(@PathVariable("userid") String userId) throws Exception {
////		// (0 : 사용 X, 1 : 사용 O)
////		System.out.println("MemberController - idCheck 파라미터 "+ userId);
////		int check = memberService.idCheck(userId);
////		System.out.println("MemberController - idCheck 함수 "+ check);
////		return check;
////	}
////	
////	@GetMapping("/pwdfind")
////	@ResponseBody
////	public String pwdfind(@RequestParam(name = "userid") String userId, @RequestParam(name = "email") String email) throws Exception {
////		System.out.println("pwdfind함수 파라미터 "+userId+" "+email);
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("userId", userId);
////		map.put("userEmail", email);
////		MemberDto memberDto = memberService.pwdFind(map);
////		System.out.println("MemberController - pwdfind 함수"+memberDto.toString());
////		String pwd = memberDto.getUserPw();
////		System.out.println("pwd:"+pwd);
////		return pwd;
////	}
////	
//////	@GetMapping("/join")
//////	public String join() {
//////		return "redirect:/user/modal";
//////	}
////	
////	@PostMapping("/join")
////	public String join(MemberDto memberDto) throws Exception{
////		System.out.println("join 파라미터"+memberDto.toString());
//////		MemberDto memberDto2 = new MemberDto();
//////		memberDto2.setUserName(memberDto.getUserName());
//////		memberDto2.setUserId(memberDto.getUserId());
//////		memberDto2.setUserPw(memberDto.getUserPw());
//////		memberDto2.setUserEmail(memberDto.getUserEmail());
//////		memberDto2.setUserPhone(memberDto.getUserPhone());
//////		System.out.println("join 결과값"+memberDto2.toString());
////		int cnt = memberService.joinMember(memberDto);
////		System.out.println("MemberController - join 함수 "+cnt);
////		return "redirect:/";
////	}
////	
//////	@GetMapping("/login")
//////	public String login() {
//////		return "redirect:/user/modal";
//////	}
////	
////	@PostMapping("/login")
////	public String login(@RequestParam(name = "login_id") String userId, @RequestParam(name = "login_pwd") String userPwd,
////			@RequestParam(name = "saveid", required = false) String idsave,
////			HttpServletRequest request, HttpServletResponse response) throws Exception{
////		System.out.println("PostMapping login 파라미터 "+userId+" "+userPwd);
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("userId", userId);
////		map.put("userPw", userPwd);		
////		try {
////			MemberDto memberDto = memberService.loginMember(map);
////			if(memberDto != null) {
////				HttpSession session = request.getSession();
////				session.setAttribute("userinfo", memberDto);
////				System.out.println("MemberController - login 함수"+memberDto.toString());
////				if("ok".equals(idsave)) {
////					Cookie cookie = new Cookie("user_id", userId);
////					cookie.setPath(request.getContextPath());
////					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
////					response.addCookie(cookie);
////				} else {
////					Cookie cookies[] = request.getCookies();
////					if(cookies != null) {
////						for(Cookie cookie : cookies) {
////							if("user_id".equals(cookie.getName())) {
////								cookie.setMaxAge(0);
////								response.addCookie(cookie);
////								break;
////							}
////						}
////					}
////				}
////				return "redirect:/";
////			} else {
////				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
////				return "redirect:/";
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			request.setAttribute("msg", "로그인 중 에러 발생!!!");
////			return "redirect:/";
////		}
////		
////	}
////	
////	@GetMapping("/logout")
////	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
////		HttpSession session = request.getSession();
////		session.invalidate();
////		return "redirect:/";
////	}
////	
////	@PostMapping("/update")
////	public String update(@RequestParam(name = "mypage_pwd") String pwd, @RequestParam(name = "mypage_email") String email,
////			HttpServletRequest request, HttpServletResponse response) throws Exception{
////		
////		
////		HttpSession session = request.getSession();
////		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
////		
////		if (memberDto != null) {
////			String userId = memberDto.getUserId();
////			String userPhone = memberDto.getUserPhone();
////			String userName = memberDto.getUserName();
////			String useremail = memberDto.getUserEmail();
////			String userPw = memberDto.getUserPw();
////			
////			MemberDto memberDto_set = new MemberDto();
////			memberDto_set.setUserPw(userPw);
////			if(pwd.length() != 0)
////				memberDto_set.setUserPw(pwd);
////			
////			memberDto_set.setUserEmail(useremail);
////			if(email.length() != 0)
////				memberDto_set.setUserEmail(email);
////
////			memberDto_set.setUserId(userId);
////			
////			try {
////				memberService.updateMember(memberDto_set);
////				memberDto_set.setUserPhone(userPhone);
////				memberDto_set.setUserName(userName);
////				memberDto_set.setUserPw("");
////				
////				session.setAttribute("userinfo", memberDto_set); // session userinfo update
////				
////				return "redirect:/";
////			} catch (Exception e) {
////				e.printStackTrace();
////				request.setAttribute("msg", "유저 정보 업데이트 중 에러 발생!!!");
////				return "redirect:/";
////			}
////		} else
////			return "redirect:/index";
////	}
////	
////	@GetMapping("/delete")
////	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
////		HttpSession session = request.getSession();
////		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
////		if (memberDto != null) {
////			String userId = memberDto.getUserId();
////			
////			try {
////				memberService.deleteMember(userId);
////				session.invalidate();
////				return "redirect:/";
////			} catch (Exception e) {
////				e.printStackTrace();
////				return "redirect:/";
////			}
////		} else
////			return "redirect:/";
////	}
//
//}
//
//
