package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.dao.MemberMeetingPositionMapper;
import com.bird.entity.Admin;
import com.bird.entity.MemberMeetingPosition;
import com.bird.service.AdminService;
import com.bird.service.MemberMeetingPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MemberMeetingPositionServiceImpl implements MemberMeetingPositionService {


	@Autowired
	private MemberMeetingPositionMapper memberMeetingPositionMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return memberMeetingPositionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MemberMeetingPosition record) {
		return 0;
	}

	@Override
	public int insertSelective(MemberMeetingPosition record) {
		return memberMeetingPositionMapper.insertSelective(record);
	}

	@Override
	public MemberMeetingPosition selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MemberMeetingPosition record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MemberMeetingPosition record) {
		return 0;
	}

	@Override
	public List<Map> getMemberMeetingPositions(Map query_map) {
		return memberMeetingPositionMapper.getMemberMeetingPositions(query_map);
	}
}
