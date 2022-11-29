package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
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

import java.util.Map;

@Controller("weixinDept")
@RequestMapping("/weixin/dept")
public class DeptController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private DeptService deptService;

	@PostMapping("/list")
	@ResponseBody
	public String index(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Map<String,Object> query_map = (Map<String, Object>) JSON.parse(jsonObject.toJSONString());
		return_data.put("meeting",deptService.getDepts(query_map));
		return  ResultUtil.success(return_data);

	}




}
