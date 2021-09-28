package com.bird.service.impl;


import com.bird.dao.ContacUsMapper;
import com.bird.dao.MeetingMapper;
import com.bird.entity.Meeting;
import com.bird.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private MeetingMapper meetingMapper;

	@Value("${meetingId}")
	private Long meetingId;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return meetingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Meeting record) {
		return 0;
	}

	@Override
	public int insertSelective(Meeting record) {
		return meetingMapper.insertSelective(record);
	}

	@Override
	public Meeting selectByPrimaryKey(Long id) {
		return meetingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Meeting record) {
		return meetingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Meeting record) {
		return 0;
	}

	@Override
	public List<Map> getMeetings(Map query_map) {
		return meetingMapper.getMeetings(query_map);
	}

	@Override
	public Meeting getMeeting() {
		return meetingMapper.selectByPrimaryKey(meetingId);
	}


}
