package com.bird.controller.weixin;

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

@Controller("weixinSchedule")
@RequestMapping("/weixin/schedule")
public class ScheduleController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ScheduleService scheduleService;

	@Value("${meetingId}")
	private Long meetingId;


	@PostMapping("/list")
	@ResponseBody
	public String list(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Map query_map = new HashMap();
		query_map.put("meetingId",meetingId);
		List<Map> schedules = scheduleService.getSchedules(query_map);
		return_data.put("schedules",schedules);
		return  ResultUtil.success(return_data);

	}






}
