package com.bird.service.impl;


import com.bird.dao.EpidemicMapper;
import com.bird.dao.OrganizationalMapper;
import com.bird.entity.Epidemic;
import com.bird.entity.Organizational;
import com.bird.service.EpidemicService;
import com.bird.service.OrganizationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrganizationalServiceImpl implements OrganizationalService {

	@Autowired
	private OrganizationalMapper organizationalMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return 0;
	}

	@Override
	public int insert(Organizational record) {
		return 0;
	}

	@Override
	public int insertSelective(Organizational record) {
		return organizationalMapper.insertSelective(record);
	}

	@Override
	public Organizational selectByPrimaryKey(Long meetingId) {
		return organizationalMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(Organizational record) {
		return organizationalMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Organizational record) {
		return 0;
	}

	@Override
	public List<Map> getOrganizationalList(Map query_map) {
		return organizationalMapper.getOrganizationalList(query_map);
	}
}
