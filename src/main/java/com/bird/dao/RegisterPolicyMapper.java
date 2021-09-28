package com.bird.dao;

import com.bird.entity.RegisterPolicy;

import java.util.List;
import java.util.Map;

public interface RegisterPolicyMapper {
    int deleteByPrimaryKey(Long meetingId);

    int insert(RegisterPolicy record);

    int insertSelective(RegisterPolicy record);

    RegisterPolicy selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(RegisterPolicy record);

    int updateByPrimaryKeyWithBLOBs(RegisterPolicy record);

    List<Map> getRegisterPolicyList(Map query_map);
}