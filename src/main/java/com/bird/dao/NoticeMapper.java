package com.bird.dao;

import com.bird.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long meetingId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long meetingId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    List<Map> getNoticeList(Map query_map);
}