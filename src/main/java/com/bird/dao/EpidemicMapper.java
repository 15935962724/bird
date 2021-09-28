package com.bird.dao;

import com.bird.entity.Epidemic;

import java.util.List;
import java.util.Map;

public interface EpidemicMapper {
    int deleteByPrimaryKey(Long meetingId);

    int insert(Epidemic record);

    int insertSelective(Epidemic record);

    Epidemic selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(Epidemic record);

    int updateByPrimaryKeyWithBLOBs(Epidemic record);

    List<Map> getEpidemicList(Map query_map);
}