package com.bird.service;

import com.bird.entity.Attract;

import java.util.List;
import java.util.Map;

public interface AttractService {
    int deleteByPrimaryKey(Long meetingId);

    int insert(Attract record);

    int insertSelective(Attract record);

    Attract selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(Attract record);

    int updateByPrimaryKeyWithBLOBs(Attract record);

    List<Map> getAttractList(Map query_map);

}