package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionDto;

public interface AttractionService {
	void insertAttraction(AttractionDto attractionDto) throws Exception;
	List<AttractionDto> searchAttractionByLatLon(float latitude, float longitude, float meter) throws Exception;
}
