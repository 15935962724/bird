package com.bird.service;

import com.bird.entity.RegisterPolicy;

import java.util.List;
import java.util.Map;

public interface RegisterPolicyService {

    int deleteByPrimaryKey(Long mettingId);

    int insert(RegisterPolicy record);

    int insertSelective(RegisterPolicy record);

    RegisterPolicy selectByPrimaryKey(Long mettingId);

    int updateByPrimaryKeySelective(RegisterPolicy record);

    int updateByPrimaryKeyWithBLOBs(RegisterPolicy record);

    List<Map> getRegisterPolicyList(Map query_map);
}