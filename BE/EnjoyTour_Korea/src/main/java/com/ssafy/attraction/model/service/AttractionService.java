package com.ssafy.attraction.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionDto;

public interface AttractionService {
	void insertAttraction(AttractionDto attractionDto) throws Exception;
	List<AttractionDto> searchAttractionByLatLon(Map<String, Float> map) throws Exception;
}
