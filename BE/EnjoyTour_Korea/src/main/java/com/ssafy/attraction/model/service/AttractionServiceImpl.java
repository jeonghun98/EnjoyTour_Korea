package com.ssafy.attraction.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.SidoGugunCodeDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService 
{
	private AttractionMapper attractionmapper;
	
//	@Autowired
//	private SqlSession sqlSession;

	private AttractionServiceImpl(AttractionMapper attractionmapper) {
		super();
		this.attractionmapper = attractionmapper;
	}

//	@Override
//	public void insertAttraction(AttractionDto attractionDto) throws Exception {
//		attractionmapper.insertAttraction(attractionDto);
//	}

	@Override
	public List<AttractionDto> searchAttractionList(Map<String, String> map) throws Exception {
		return attractionmapper.searchAttractionList(map);
	}

	@Override
	public List<AttractionDto> searchAttractionWordList(Map<String, String> map) throws Exception {
		return attractionmapper.searchAttractionWordList(map);
	}
	
	@Override
	public AttractionDto searchAttraction(String contentId) throws Exception {
		return attractionmapper.searchAttraction(contentId);
	}
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return attractionmapper.getSido();
	}
	
	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return attractionmapper.getGugunInSido(sido);
	}
	
	@Override
	public List<AttractionDto> getAttractionInGugun(String gugun) throws Exception {
		return attractionmapper.getAttractionInGugun(gugun);
	}

}
