package com.bird.service;

import com.bird.entity.Meeting;

import java.util.List;
import java.util.Map;

public interface MeetingService {
    int deleteByPrimaryKey(Long id);

    int insert(Meeting record);

    int insertSelective(Meeting record);

    Meeting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKey(Meeting record);

    List<Map> getMeetings(Map query_map);

    Meeting getMeeting();
}