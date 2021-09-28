package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.entity.Admin;
import com.bird.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Admin record) {
		return 0;
	}

	@Override
	public int insertSelective(Admin record) {
		return 0;
	}

	@Override
	public Admin selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Admin record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Admin record) {
		return 0;
	}

	@Override
	public Admin selectByUserName(String userName) {
		return adminMapper.selectByUserName(userName);
	}
}
