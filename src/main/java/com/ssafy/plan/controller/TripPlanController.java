//package com.ssafy.plan.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.member.model.MemberDto;
//import com.ssafy.plan.model.TripPlanDto;
//
///**
// * Servlet implementation class TripPlanController
// */
//@WebServlet("/tripplan")
//public class TripPlanController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    public TripPlanController() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String action = request.getParameter("action");
//		String path = "";
////		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//
//		switch(action) {
//		case "write":
//			path = "/plan/write.jsp";
//			forward(request, response, path);
//			break;
//		case "save":
//			path = "/tripplan?action=write";
//			redirect(request, response, path);
//			break;
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//	
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		System.out.println("PUT");
//		BufferedReader reader = request.getReader();
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = reader.readLine()) != null) {
//			sb.append(line);
//		}
//		String jsonData = sb.toString();
//		ObjectMapper mapper = new ObjectMapper();
//
//		TripPlanDto dto = new TripPlanDto();
//        TripPlanDto.TripList list = dto.getTripList();
//		list.setAttractionList(mapper.readValue(jsonData, String[].class));
//		System.out.println(list);
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
//
//}
