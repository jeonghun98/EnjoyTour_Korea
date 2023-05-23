package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.SidoGugunCodeDto;

@Mapper
public interface AttractionMapper {
//	void insertAttraction(AttractionDto attractionDto) throws SQLException;
	List<AttractionDto> searchAttractionList(Map<String, String> map) throws Exception;
	List<AttractionDto> searchAttractionWordList(Map<String, String> map) throws Exception;
	AttractionDto searchAttraction(String contentId) throws Exception;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<AttractionDto> getAttractionInGugun(String gugun) throws SQLException;
}
