package com.bird.service.impl;


import com.bird.dao.ContacUsMapper;
import com.bird.dao.RegisterPolicyMapper;
import com.bird.entity.ContacUs;
import com.bird.entity.RegisterPolicy;
import com.bird.service.ContacUsService;
import com.bird.service.RegisterPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RegisterPolicyServiceImpl implements RegisterPolicyService {

	@Autowired
	private RegisterPolicyMapper registerPolicyMapper;

	@Override
	public int deleteByPrimaryKey(Long mettingId) {
		return registerPolicyMapper.deleteByPrimaryKey(mettingId);
	}

	@Override
	public int insert(RegisterPolicy record) {
		return 0;
	}

	@Override
	public int insertSelective(RegisterPolicy record) {
		return registerPolicyMapper.insertSelective(record);
	}

	@Override
	public RegisterPolicy selectByPrimaryKey(Long mettingId) {
		return registerPolicyMapper.selectByPrimaryKey(mettingId);
	}

	@Override
	public int updateByPrimaryKeySelective(RegisterPolicy record) {
		return registerPolicyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(RegisterPolicy record) {
		return 0;
	}

	@Override
	public List<Map> getRegisterPolicyList(Map query_map) {
		return registerPolicyMapper.getRegisterPolicyList(query_map);
	}
}
