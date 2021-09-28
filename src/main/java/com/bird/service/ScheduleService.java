package com.bird.service;

import com.bird.entity.Schedule;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    int deleteByPrimaryKey(Long id);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    List<Map> getSchedules(Map query_map);
}