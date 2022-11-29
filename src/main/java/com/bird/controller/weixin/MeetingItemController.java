package com.bird.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.bird.entity.*;
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

@Controller("weixinMeetingItem")
@RequestMapping("/weixin/meetingItem")
public class MeetingItemController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private MeetingItemServive meetingItemServive;

	@Value("${meetingId}")
	private Long meetingId;


	/**
	 * 会场交通
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/list")
	@ResponseBody
	public String list(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Map query_map = new HashMap();
		query_map.put("meetingId",meetingId);
		List<Map> meetingItems = meetingItemServive.getMeetingItems(query_map);
		return_data.put("meetingItems",meetingItems);
		return  ResultUtil.success(return_data);
	}





}
