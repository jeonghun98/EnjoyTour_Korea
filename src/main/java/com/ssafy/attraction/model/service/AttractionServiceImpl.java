//package com.ssafy.attraction.model.service;
//
//import java.util.List;
//
//import com.ssafy.attraction.model.AttractionDto;
//import com.ssafy.attraction.model.dao.AttractionDao;
//import com.ssafy.attraction.model.dao.AttractionDaoImpl;
//
//public class AttractionServiceImpl implements AttractionService 
//{
//	private static AttractionService attractionService = new  AttractionServiceImpl();
//	private AttractionDao attractionDao;
//
//	private AttractionServiceImpl() {
//		attractionDao = AttractionDaoImpl.getAttractionDao();
//	}
//
//	public static AttractionService getAttractionService() {
//		return attractionService;
//	}
//
//	@Override
//	public void insertAttraction(AttractionDto attractionDto) throws Exception {
//		attractionDao.insertAttraction(attractionDto);
//	}
//
//	@Override
//	public List<AttractionDto> searchAttractionByLatLon(float latitude, float longitude, float meter) throws Exception {
//		return attractionDao.searchAttractionByLatLon(latitude, longitude, meter);
//	}
//}
