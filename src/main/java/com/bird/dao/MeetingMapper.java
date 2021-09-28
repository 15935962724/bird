package com.bird.dao;

import com.bird.entity.Meeting;

import java.util.List;
import java.util.Map;

public interface MeetingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Meeting record);

    int insertSelective(Meeting record);

    Meeting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKeyWithBLOBs(Meeting record);

    int updateByPrimaryKey(Meeting record);

    List<Map> getMeetings(Map query_map);
}