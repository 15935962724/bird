package com.bird.service;

import com.bird.entity.MemberMeetingPosition;

import java.util.List;
import java.util.Map;

public interface MemberMeetingPositionService {

    int deleteByPrimaryKey(Long id);

    int insert(MemberMeetingPosition record);

    int insertSelective(MemberMeetingPosition record);

    MemberMeetingPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberMeetingPosition record);

    int updateByPrimaryKey(MemberMeetingPosition record);

    List<Map> getMemberMeetingPositions(Map query_map);

}