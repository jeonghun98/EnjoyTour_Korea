package com.ssafy.hotplace.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.FileInfoDto;
import com.ssafy.hotplace.model.HotplaceDto;

@Mapper
public interface HotplaceMapper {

	void writeHotplace(HotplaceDto hotplaceDto) throws Exception;
	List<HotplaceDto> listHotplace() throws Exception;
	HotplaceDto getHotplace(int hotplaceNo) throws Exception;
	void updateLike(int like) throws Exception;
	void modifyHotplace(HotplaceDto hotplaceDto) throws Exception;
	void deleteHotplace(int hotplaceNo) throws Exception;
	
	// -- 파일 설정
	void registerFile(HotplaceDto hotplaceDto) throws Exception;
	void deleteFile(int hotplaceNo) throws Exception;
	List<FileInfoDto> fileInfoList(int hotplaceNo) throws Exception;
}
