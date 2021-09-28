package com.bird.dao;

import com.bird.entity.MeetingPosition;

public interface MeetingPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MeetingPosition record);

    int insertSelective(MeetingPosition record);

    MeetingPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MeetingPosition record);

    int updateByPrimaryKey(MeetingPosition record);
}