package com.bird.dao;

import com.bird.entity.Organizational;

import java.util.List;
import java.util.Map;

public interface OrganizationalMapper {
    int deleteByPrimaryKey(Long meetingId);

    int insert(Organizational record);

    int insertSelective(Organizational record);

    Organizational selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(Organizational record);

    int updateByPrimaryKeyWithBLOBs(Organizational record);

    List<Map> getOrganizationalList(Map query_map);
}