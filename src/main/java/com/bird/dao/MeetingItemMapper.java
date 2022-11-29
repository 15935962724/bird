package com.bird.dao;

import com.bird.entity.MeetingItem;

import java.util.List;
import java.util.Map;

public interface MeetingItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MeetingItem record);

    int insertSelective(MeetingItem record);

    MeetingItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MeetingItem record);

    int updateByPrimaryKey(MeetingItem record);

    List<Map> getMeetingItems(Map query_map);

}