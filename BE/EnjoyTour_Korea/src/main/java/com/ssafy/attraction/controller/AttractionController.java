package com.ssafy.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;

@Controller
@RequestMapping("/attraction")
public class AttractionController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	private AttractionService attractionservice;
	
	@Autowired
	public AttractionController(AttractionService attractionservice) {
		super();
		this.attractionservice = attractionservice;
	}

	@GetMapping("/view")
	public String view(Model model)
			throws Exception {
		return "attraction/attraction";
	}
	
	@ResponseBody
	@GetMapping("/searchByLoc")
	public ResponseEntity<?> searchByLoc(@RequestParam Float mapX, @RequestParam Float mapY, @RequestParam Float radius, Model model) {
		logger.debug("searchByLoc call");
		try {
			Map<String, Float> map = new HashMap<String, Float>();
			map.put("latitude", mapY);
			map.put("longitude", mapX);
			map.put("meter", radius);
			List<AttractionDto> list = attractionservice.searchAttractionByLatLon(map);
			if(list != null && !list.isEmpty()) {
				System.out.println(list.size());
				return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

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
}
