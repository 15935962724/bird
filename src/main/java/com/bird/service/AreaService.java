package com.bird.service;

import com.bird.entity.Area;

import java.util.List;
import java.util.Map;

public interface AreaService {
    int deleteByPrimaryKey(Long id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Long id);

    Area getFullName(String name);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKeyWithBLOBs(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> getAreas(Map query_map);

    String getTree_path(Long id);






}