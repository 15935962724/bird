package com.bird.service.impl;


import com.bird.dao.AreaMapper;
import com.bird.dao.DeptMapper;
import com.bird.entity.Area;
import com.bird.entity.Dept;
import com.bird.service.AreaService;
import com.bird.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DeptServiceImpl implements DeptService {


	@Autowired
	private DeptMapper deptMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Dept record) {
		return 0;
	}

	@Override
	public int insertSelective(Dept record) {
		return 0;
	}

	@Override
	public Dept selectByPrimaryKey(Long id) {
		return deptMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Dept record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Dept record) {
		return 0;
	}

	@Override
	public List<Dept> getDepts(Map query_map) {
		return deptMapper.getDepts(query_map);
	}
}
