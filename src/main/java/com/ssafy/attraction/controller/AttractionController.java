//package com.ssafy.attraction.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ssafy.attraction.model.AttractionDto;
//import com.ssafy.attraction.model.service.AttractionService;
//import com.ssafy.attraction.model.service.AttractionServiceImpl;
//import java.io.File;
//import java.io.IOException;
//
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * Servlet implementation class AttractionController
// */
//@WebServlet("/attraction")
//public class AttractionController extends HttpServlet {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private AttractionService attractionService = AttractionServiceImpl.getAttractionService();
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		String path = "";
//
//		System.out.println("action: " + action + "");
//
//		if ("view".equals(action)) {
//			path = "/attraction/attraction.jsp";
//			forward(request, response, path);
//		} else if ("searchByLoc".equals(action)) {
//			searchByLoc(request, response);
////			path = "/attraction/attraction.jsp";
////			forward(request, response, path);
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//
//	protected void doPut(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		System.out.println("PUT");
//		BufferedReader reader = request.getReader();
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = reader.readLine()) != null) {
//			sb.append(line);
//		}
//		String jsonData = sb.toString();
//
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Map<String, Map<String, Map<String, Object>>>> result = mapper.readValue(jsonData, Map.class);
//
//		@SuppressWarnings("unchecked")
//		List<Map<String, Object>> list = (List) (result.get("response").get("body").get("items").get("item"));
//		for (Map<String, Object> info : list) {
//			try {
//				AttractionDto attractionDto = mapper.convertValue(info, AttractionDto.class);
//				attractionService.insertAttraction(attractionDto);
//				Thread.sleep(2);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
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
//	protected String insert(HttpServletRequest request, HttpServletResponse response) {
//		String path = "/attraction/attraction.jsp";
//		return path;
//	}
//
//	private void searchByLoc(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		ObjectMapper objectMapper = new ObjectMapper();
//		float latitude = Float.parseFloat(request.getParameter("mapY"));
//		float longitude = Float.parseFloat(request.getParameter("mapX"));
//		float meter = Float.parseFloat(request.getParameter("radius"));
//
//		try {
//			List<AttractionDto> list = attractionService.searchAttractionByLatLon(latitude, longitude, meter);
//			System.out.println(list.size());
//			String result = objectMapper.writeValueAsString(list);
//			//System.out.println(result);
//			response.getWriter().write(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
