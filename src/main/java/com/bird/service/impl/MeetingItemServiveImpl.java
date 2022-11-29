package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.dao.MeetingItemMapper;
import com.bird.dao.ScheduleMapper;
import com.bird.entity.Admin;
import com.bird.entity.MeetingItem;
import com.bird.service.AdminService;
import com.bird.service.MeetingItemServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MeetingItemServiveImpl implements MeetingItemServive {

	@Autowired
	private MeetingItemMapper meetingItemMapper;

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {

		return meetingItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MeetingItem record) {
		return 0;
	}

	@Override
	public int insertSelective(MeetingItem record) {
		return meetingItemMapper.insertSelective(record);
	}

	@Override
	public MeetingItem selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MeetingItem record) {
		return meetingItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MeetingItem record) {
		return 0;
	}

	@Override
	public List<Map> getMeetingItems(Map query_map) {
		return meetingItemMapper.getMeetingItems(query_map);
	}
}
