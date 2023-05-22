package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.SidoGugunCodeDto;

public interface AttractionService {
//	void insertAttraction(AttractionDto attractionDto) throws Exception;
	List<AttractionDto> searchAttractionList(Map<String, String> map) throws Exception;
	List<AttractionDto> searchAttractionWordList(Map<String, String> map) throws Exception;
	AttractionDto searchAttraction(String contentid) throws Exception;
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<AttractionDto> getAttractionInGugun(String gugun) throws Exception;
}
