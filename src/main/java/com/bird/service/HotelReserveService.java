package com.bird.service;

import com.bird.entity.HotelReserve;

import java.util.List;
import java.util.Map;

public interface HotelReserveService {
    int deleteByPrimaryKey(Long meetingId);

    int insert(HotelReserve record);

    int insertSelective(HotelReserve record);

    HotelReserve selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(HotelReserve record);

    int updateByPrimaryKeyWithBLOBs(HotelReserve record);

    List<Map> getHotelReserveList(Map query_map);
}