package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.*;
import com.bird.service.*;
import com.bird.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.startup.HomesUserDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("weixinMeeting")
@RequestMapping("/weixin/meeting")
public class MeetingController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private RegisterPolicyService registerPolicyService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private HotelReserveService hotelReserveService;

	@Autowired
	private ContacUsService contacUsService;

	@Autowired
	private AttractService attractService;

	@Autowired
	private EpidemicService epidemicService;

	@Autowired
	private OrganizationalService organizationalService;

	@Autowired
	private TrafficService trafficService;

	@Value("${meetingId}")
	private Long meetingId;


	@PostMapping("/index")
	@ResponseBody
	public String index(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		return_data.put("meeting",meetingService.getMeeting());
		return  ResultUtil.success(return_data);

	}


	/**
	 * 注册须知
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/registerPolicy")
	@ResponseBody
	public String registerPolicy(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		RegisterPolicy registerPolicy = registerPolicyService.selectByPrimaryKey(meetingId);
		return_data.put("registerPolicy",registerPolicy);
		return  ResultUtil.success(return_data);

	}


	/**
	 * 会议通知
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/notice")
	@ResponseBody
	public String notice(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Notice notice = noticeService.selectByPrimaryKey(meetingId);
		return_data.put("notice",notice);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 酒店预订
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/hotelReserve")
	@ResponseBody
	public String hotelReserve(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		HotelReserve hotelReserve = hotelReserveService.selectByPrimaryKey(meetingId);
		return_data.put("hotelReserve",hotelReserve);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 联系我们
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/contacUs")
	@ResponseBody
	public String contacUs(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		ContacUs contacUs = contacUsService.selectByPrimaryKey(meetingId);
		return_data.put("contacUs",contacUs);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 会议招商
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/attract")
	@ResponseBody
	public String attract(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Attract attract = attractService.selectByPrimaryKey(meetingId);
		return_data.put("attract",attract);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 疫情自查
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/epidemic")
	@ResponseBody
	public String epidemic(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Epidemic epidemic = epidemicService.selectByPrimaryKey(meetingId);
		return_data.put("epidemic",epidemic);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 组织架构
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/organizational")
	@ResponseBody
	public String organizational(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Organizational organizational = organizationalService.selectByPrimaryKey(meetingId);
		return_data.put("organizational",organizational);
		return  ResultUtil.success(return_data);
	}

	/**
	 * 会场交通
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/traffic")
	@ResponseBody
	public String traffic(@RequestBody JSONObject jsonObject){
		JSONObject return_data = new JSONObject();
		Traffic traffic = trafficService.selectByPrimaryKey(meetingId);
		return_data.put("traffic",traffic);
		return  ResultUtil.success(return_data);
	}





}
