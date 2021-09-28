package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.dao.AreaMapper;
import com.bird.entity.Admin;
import com.bird.entity.Area;
import com.bird.service.AdminService;
import com.bird.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Area record) {
		return 0;
	}

	@Override
	public int insertSelective(Area record) {
		return 0;
	}

	@Override
	public Area selectByPrimaryKey(Long id) {
		return areaMapper.selectByPrimaryKey(id);
	}

	@Override
	public Area getFullName(String name) {
		return areaMapper.getFullName(name);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Area record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		return 0;
	}

	@Override
	public List<Area> getAreas(Map query_map) {
		return areaMapper.getAreas(query_map);
	}

	String tree_path = "";
	@Override
	public String getTree_path(Long id) {
		Area area = areaMapper.selectByPrimaryKey(id);
		if (area.getParent()!=0){
			getTree_path(area.getParent());
		}else {
			tree_path = area.getId().toString();
		}
		return tree_path;
	}
}
