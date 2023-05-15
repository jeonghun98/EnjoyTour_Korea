package com.ssafy.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.attraction.model.AttractionDto;

@Mapper
public interface AttractionMapper {
	void insertAttraction(AttractionDto attractionDto) throws SQLException;
	List<AttractionDto> searchAttractionByLatLon(Map<String, Float> map) throws Exception;
}
