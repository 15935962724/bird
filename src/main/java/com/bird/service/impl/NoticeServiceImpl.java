package com.bird.service.impl;


import com.bird.dao.HotelReserveMapper;
import com.bird.dao.NoticeMapper;
import com.bird.entity.HotelReserve;
import com.bird.entity.Notice;
import com.bird.service.HotelReserveService;
import com.bird.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return noticeMapper.deleteByPrimaryKey(meetingId);
	}

	@Override
	public int insert(Notice record) {
		return 0;
	}

	@Override
	public int insertSelective(Notice record) {
		return noticeMapper.insertSelective(record);
	}

	@Override
	public Notice selectByPrimaryKey(Long meetingId) {
		return noticeMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Notice record) {
		return 0;
	}

	@Override
	public List<Map> getNoticeList(Map query_map) {
		return noticeMapper.getNoticeList(query_map);
	}
}
