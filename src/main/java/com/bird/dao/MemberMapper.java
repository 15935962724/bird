package com.bird.dao;

import com.bird.entity.Member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    Member getMember(Map query_map);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Map> getMembers(Map query_map);
}