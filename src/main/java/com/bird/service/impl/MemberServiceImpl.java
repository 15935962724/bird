package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.dao.MemberMapper;
import com.bird.entity.Admin;
import com.bird.entity.Member;
import com.bird.service.AdminService;
import com.bird.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MemberServiceImpl implements MemberService {


	@Autowired
	private MemberMapper memberMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Member record) {
		return 0;
	}

	@Override
	public int insertSelective(Member record) {
		return memberMapper.insertSelective(record);
	}

	@Override
	public Member selectByPrimaryKey(Long id) {
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public Member getMember(Map query_map) {
		return memberMapper.getMember(query_map);
	}

	@Override
	public int updateByPrimaryKeySelective(Member record) {
		return memberMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map> getMembers(Map query_map) {
		return memberMapper.getMembers(query_map);
	}

	@Override
	public int updateByPrimaryKey(Member record) {
		return 0;
	}
}
