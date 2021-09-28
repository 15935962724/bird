package com.bird.dao;

import com.bird.entity.Traffic;

import java.util.List;
import java.util.Map;

public interface TrafficMapper {
    int deleteByPrimaryKey(Long meetingId);

    int insert(Traffic record);

    int insertSelective(Traffic record);

    Traffic selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(Traffic record);

    int updateByPrimaryKeyWithBLOBs(Traffic record);

    List<Map> getTrafficList(Map query);
}