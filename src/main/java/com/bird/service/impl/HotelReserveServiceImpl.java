package com.bird.service.impl;


import com.bird.dao.ContacUsMapper;
import com.bird.dao.HotelReserveMapper;
import com.bird.entity.ContacUs;
import com.bird.entity.HotelReserve;
import com.bird.service.ContacUsService;
import com.bird.service.HotelReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class HotelReserveServiceImpl implements HotelReserveService {

	@Autowired
	private HotelReserveMapper hotelReserveMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return hotelReserveMapper.deleteByPrimaryKey(meetingId);
	}

	@Override
	public int insert(HotelReserve record) {
		return 0;
	}

	@Override
	public int insertSelective(HotelReserve record) {
		return hotelReserveMapper.insertSelective(record);
	}

	@Override
	public HotelReserve selectByPrimaryKey(Long meetingId) {
		return hotelReserveMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(HotelReserve record) {
		return hotelReserveMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(HotelReserve record) {
		return 0;
	}

	@Override
	public List<Map> getHotelReserveList(Map query_map) {
		return hotelReserveMapper.getHotelReserveList(query_map);
	}
}
