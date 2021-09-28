package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.ContacUs;
import com.bird.entity.HotelReserve;
import com.bird.entity.Notice;
import com.bird.entity.RegisterPolicy;
import com.bird.service.*;
import com.bird.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("weixinMember")
@RequestMapping("/weixin/member")
public class MemberController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private MemberMeetingPositionService memberMeetingPositionService;

	@Value("${meetingId}")
	private Long meetingId;


	/**
	 * 主席介绍      讲者&主持
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/list")
	@ResponseBody
	public String list(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		System.out.println("weixin/member/list:"+jsonObject.toString());
		Map query_map = (Map) JSON.parse(jsonObject.toJSONString());
		query_map.put("meetingId",meetingId);
		List<Map> memberMeetingPositions = memberMeetingPositionService.getMemberMeetingPositions(query_map);
		return_data.put("memberMeetingPositions",memberMeetingPositions);
		return  ResultUtil.success(return_data);

	}






}
