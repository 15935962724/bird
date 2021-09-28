package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.entity.Admin;
import com.bird.entity.MeetingPosition;
import com.bird.service.AdminService;
import com.bird.service.MeetingPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MeetingPositionServiceImpl implements MeetingPositionService {




	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(MeetingPosition record) {
		return 0;
	}

	@Override
	public int insertSelective(MeetingPosition record) {
		return 0;
	}

	@Override
	public MeetingPosition selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MeetingPosition record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MeetingPosition record) {
		return 0;
	}
}
