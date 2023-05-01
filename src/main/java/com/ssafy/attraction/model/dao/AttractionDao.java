package com.ssafy.attraction.model.dao;

import com.ssafy.attraction.model.AttractionDto;
import java.sql.SQLException;
import java.util.List;

public interface AttractionDao {
    void insertAttraction(AttractionDto attractionDto) throws SQLException;
	List<AttractionDto> searchAttractionByLatLon(float latitude, float longitude, float meter) throws Exception;
}