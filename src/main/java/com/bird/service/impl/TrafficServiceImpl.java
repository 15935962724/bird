package com.bird.service.impl;


import com.bird.dao.OrganizationalMapper;
import com.bird.dao.TrafficMapper;
import com.bird.entity.Organizational;
import com.bird.entity.Traffic;
import com.bird.service.OrganizationalService;
import com.bird.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TrafficServiceImpl implements TrafficService {

	@Autowired
	private TrafficMapper trafficMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return 0;
	}

	@Override
	public int insert(Traffic record) {
		return 0;
	}

	@Override
	public int insertSelective(Traffic record) {
		return trafficMapper.insertSelective(record);
	}

	@Override
	public Traffic selectByPrimaryKey(Long meetingId) {
		return trafficMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(Traffic record) {
		return trafficMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Traffic record) {
		return 0;
	}

	@Override
	public List<Map> getTrafficList(Map query) {
		return trafficMapper.getTrafficList(query);
	}
}
