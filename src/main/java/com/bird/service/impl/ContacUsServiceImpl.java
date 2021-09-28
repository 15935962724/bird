package com.bird.service.impl;


import com.bird.dao.AreaMapper;
import com.bird.dao.ContacUsMapper;
import com.bird.entity.Area;
import com.bird.entity.ContacUs;
import com.bird.service.AreaService;
import com.bird.service.ContacUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ContacUsServiceImpl implements ContacUsService {

	@Autowired
	private ContacUsMapper contacUsMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return contacUsMapper.deleteByPrimaryKey(meetingId);
	}

	@Override
	public int insert(ContacUs record) {
		return 0;
	}

	@Override
	public int insertSelective(ContacUs record) {
		return contacUsMapper.insertSelective(record);
	}

	@Override
	public ContacUs selectByPrimaryKey(Long meetingId) {
		return contacUsMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(ContacUs record) {
		return contacUsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ContacUs record) {
		return 0;
	}

	@Override
	public List<Map> getContacUsList(Map query_map) {
		return contacUsMapper.getContacUsList(query_map);
	}
}
