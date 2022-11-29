package com.bird.service;

import com.bird.entity.Area;
import com.bird.entity.Dept;

import java.util.List;
import java.util.Map;

public interface DeptService {


    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> getDepts(Map query_map);


}