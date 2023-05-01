package com.ssafy.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao {

    private static AttractionDao attractionDao = new AttractionDaoImpl();
    private DBUtil dbUtil;

    private AttractionDaoImpl() {
        dbUtil = DBUtil.getInstance();
    }

    public static AttractionDao getAttractionDao() {
        return attractionDao;
    }

    @Override
    public void insertAttraction(AttractionDto attractionDto) throws SQLException {
        insertAttractionInfo(attractionDto);
    }

    public void insertAttractionInfo(AttractionDto attractionDto) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String[] parameter = { Integer.toString(attractionDto.getContentid()), Integer.toString(attractionDto.getContenttypeid()), attractionDto.getTitle(),
                attractionDto.getAddr1(), attractionDto.getAddr2(), 
                attractionDto.getFirstImage(), attractionDto.getFirstImage2(),
                Float.toString(attractionDto.getLatitude()),
                Float.toString(attractionDto.getLongitude()) };
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            {
                sql.append("INSERT INTO attraction_info \n");
                sql.append(
                        "(content_id, content_type_id, title, addr1, addr2, first_image, first_image2, latitude, longitude)\n");
                sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)\n");
            }

            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < parameter.length; i++) {
                pstmt.setString(i + 1, parameter[i]);
            }
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt);
        }
    }

    public void insertAttractionIntoDescription(AttractionDto attractionDto) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String[] parameter = { Integer.toString(attractionDto.getContentid()), attractionDto.getOverview() };
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            {
                sql.append("INSERT INTO attraction_description \n");
                sql.append("(content_id, overview) \n");
                sql.append("VALUES (?, ?) \n");
                
            }
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < parameter.length; i++) {
                pstmt.setString(i + 1, parameter[i]);
            }
            pstmt.executeUpdate();
        } finally {
            dbUtil.close(conn, pstmt);
        }
    }
	public List<AttractionDto> searchAttractionByLatLon(float latitude, float longitude, float meter) throws Exception{
		List<AttractionDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbUtil.getConnection();
            StringBuilder sql = new StringBuilder();
            {
            	sql.append("SELECT * FROM attraction_info ai, attraction_description ad \n")  ;
            	sql.append("WHERE ai.content_id = ad.content_id and  ST_Distance_Sphere(POINT(longitude, latitude), POINT(?, ?)) <= ?;");
            }
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setFloat(1, longitude);
            pstmt.setFloat(2, latitude);
            pstmt.setFloat(3, meter);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	list.add(getAttractionDto(rs));
            }
        } finally {
        	dbUtil.close(rs, pstmt, conn);
        }

		return list;
	}
	public AttractionDto getAttractionDto(ResultSet rs) throws SQLException {
		AttractionDto dto = new AttractionDto();
		  dto.setContentid(rs.getInt("content_id"));
		  dto.setContenttypeid(rs.getInt("content_type_id"));
		  dto.setTitle (rs.getString("title"));
		  dto.setAddr1 (rs.getString("addr1"));
		  dto.setAddr2 (rs.getString("addr2"));
		  dto.setFirstImage(rs.getString("first_image"));
		  dto.setFirstImage2(rs.getString("first_image2"));
		  dto.setLatitude (rs.getFloat("latitude"));
		  dto.setLongitude (rs.getFloat("longitude"));
		  dto.setOverview(rs.getString("overview"));
		return dto;
	}

}
