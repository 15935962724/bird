package com.bird.service.impl;


import com.bird.dao.AttractMapper;
import com.bird.dao.ContacUsMapper;
import com.bird.entity.Attract;
import com.bird.entity.ContacUs;
import com.bird.service.AttractService;
import com.bird.service.ContacUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AttractServiceImpl implements AttractService {

	@Autowired
	private AttractMapper attractMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return 0;
	}

	@Override
	public int insert(Attract record) {
		return 0;
	}

	@Override
	public int insertSelective(Attract record) {
		return attractMapper.insertSelective(record);
	}

	@Override
	public Attract selectByPrimaryKey(Long meetingId) {
		return attractMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(Attract record) {
		return attractMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Attract record) {
		return 0;
	}

	@Override
	public List<Map> getAttractList(Map query_map) {
		return attractMapper.getAttractList(query_map);
	}
}
