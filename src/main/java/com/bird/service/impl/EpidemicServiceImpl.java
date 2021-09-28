package com.bird.service.impl;


import com.bird.dao.AttractMapper;
import com.bird.dao.EpidemicMapper;
import com.bird.entity.Attract;
import com.bird.entity.Epidemic;
import com.bird.service.AttractService;
import com.bird.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EpidemicServiceImpl implements EpidemicService {

	@Autowired
	private EpidemicMapper epidemicMapper;

	@Override
	public int deleteByPrimaryKey(Long meetingId) {
		return 0;
	}

	@Override
	public int insert(Epidemic record) {
		return 0;
	}

	@Override
	public int insertSelective(Epidemic record) {
		return epidemicMapper.insertSelective(record);
	}

	@Override
	public Epidemic selectByPrimaryKey(Long meetingId) {
		return epidemicMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public int updateByPrimaryKeySelective(Epidemic record) {
		return epidemicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Epidemic record) {
		return 0;
	}

	@Override
	public List<Map> getEpidemicList(Map query_map) {
		return epidemicMapper.getEpidemicList(query_map);
	}
}
