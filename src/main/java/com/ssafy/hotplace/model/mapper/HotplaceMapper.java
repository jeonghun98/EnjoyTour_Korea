package com.ssafy.hotplace.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.HotplaceDto;

@Mapper
public interface HotplaceMapper {

	void writeHotplace(HotplaceDto hotplaceDto) throws Exception;
	List<HotplaceDto> listHotplace() throws Exception;
	HotplaceDto getHotplace(int hotplaceNo) throws Exception;
	void updateLike(int like) throws Exception;
	void modifyHotplace(HotplaceDto hotplaceDto) throws Exception;
	void deleteHotplace(int hotplaceNo) throws Exception;
	
}
