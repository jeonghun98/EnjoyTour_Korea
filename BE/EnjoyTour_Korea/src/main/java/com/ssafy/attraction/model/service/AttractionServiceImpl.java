package com.ssafy.attraction.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService 
{
	private AttractionMapper attractionmapper;
	

	private AttractionServiceImpl(AttractionMapper attractionmapper) {
		super();
		this.attractionmapper = attractionmapper;
	}

	@Override
	public void insertAttraction(AttractionDto attractionDto) throws Exception {
		attractionmapper.insertAttraction(attractionDto);
	}

	@Override
	public List<AttractionDto> searchAttractionByLatLon(Map<String, Float> map) throws Exception {
		return attractionmapper.searchAttractionByLatLon(map);
	}
}
