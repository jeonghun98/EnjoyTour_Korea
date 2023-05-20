package com.ssafy.hotplace.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.hotplace.model.HotplaceDto;

public interface HotplaceService {

	void writeHotplace(HotplaceDto hotplaceDto) throws Exception;
		
	List<HotplaceDto> listHotplace() throws Exception;
	HotplaceDto getHotplace(int hotplaceNo) throws Exception;
	void updateLike(int like) throws Exception;
	void modifyHotplace(HotplaceDto hotplaceDto) throws Exception;
	
	void deleteHotplace(int hotplaceNo, String path) throws Exception;
	
//	HotplaceDto getHotplaceNo(HotplaceDto hotplaceDto) throws Exception;

}
