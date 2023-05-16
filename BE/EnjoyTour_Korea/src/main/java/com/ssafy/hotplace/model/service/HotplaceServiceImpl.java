package com.ssafy.hotplace.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hotplace.model.FileInfoDto;
import com.ssafy.hotplace.model.HotplaceDto;
import com.ssafy.hotplace.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService {
	
	private HotplaceMapper hotplaceMapper;

	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	@Transactional
	public void writeHotplace(HotplaceDto hotplaceDto) throws Exception {
		hotplaceMapper.writeHotplace(hotplaceDto);		
	}

	@Override
	public List<HotplaceDto> listHotplace() throws Exception {
		return hotplaceMapper.listHotplace();
	}

	@Override
	public HotplaceDto getHotplace(int hotplaceNo) throws Exception {
		return hotplaceMapper.getHotplace(hotplaceNo);
	}

	@Override
	public void updateLike(int like) throws Exception {
		hotplaceMapper.updateLike(like);
	}

	@Override
	public void modifyHotplace(HotplaceDto hotplaceDto) throws Exception {
		hotplaceMapper.modifyHotplace(hotplaceDto);		
	}

	@Override
	@Transactional
	public void deleteHotplace(int hotplaceNo) throws Exception {
		hotplaceMapper.deleteHotplace(hotplaceNo);
	}

}
