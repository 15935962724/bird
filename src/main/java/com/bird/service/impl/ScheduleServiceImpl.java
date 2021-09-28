package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.dao.ScheduleMapper;
import com.bird.entity.Admin;
import com.bird.entity.Schedule;
import com.bird.service.AdminService;
import com.bird.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return scheduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Schedule record) {
		return 0;
	}

	@Override
	public int insertSelective(Schedule record) {
		return scheduleMapper.insertSelective(record);
	}

	@Override
	public Schedule selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Schedule record) {
		return scheduleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Schedule record) {
		return 0;
	}

	@Override
	public List<Map> getSchedules(Map query_map) {
		return scheduleMapper.getSchedules(query_map);
	}
}
