package com.bird.service;

import com.bird.entity.ContacUs;

import java.util.List;
import java.util.Map;

public interface ContacUsService {
    int deleteByPrimaryKey(Long meetingId);

    int insert(ContacUs record);

    int insertSelective(ContacUs record);

    ContacUs selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(ContacUs record);

    int updateByPrimaryKeyWithBLOBs(ContacUs record);

    List<Map> getContacUsList(Map query_map);
}