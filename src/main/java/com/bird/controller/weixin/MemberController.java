package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.*;
import com.bird.service.*;
import com.bird.util.ResultUtil;
import com.bird.util.SendCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller("weixinMember")
@RequestMapping("/weixin/member")
public class MemberController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private MemberMeetingPositionService memberMeetingPositionService;

	@Autowired
	private MemberService memberService;

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


	@PostMapping("/update")
	@ResponseBody
	public String update(@RequestBody JSONObject jsonObject){
		String code = jsonObject.getString("code");
		String phone = jsonObject.getString("phone");
		Subject subject = SecurityUtils.getSubject();
		String sessionCode = (String) subject.getSession().getAttribute(phone);
		if (!code.equals(sessionCode)){
			return ResultUtil.error("验证码输入有误");
		}

		Member member = memberService.getCurrent();
		member.setPhone(phone);
		member.setName(jsonObject.getString("name"));
		member.setHospital(jsonObject.getString("hospital"));
		member.setDepartment(jsonObject.getString("department"));
		memberService.updateByPrimaryKeySelective(member);
		return  ResultUtil.success("注册成功！缴费详情请点击个人中心查看");

	}

	/**
	 * 发送短信验证码
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/sendCode")
	@ResponseBody
	public String sendCode(@RequestBody JSONObject jsonObject){

		Subject subject = SecurityUtils.getSubject();
		String phone = jsonObject.getString("phone");
		Map<String,String> map = new HashMap<>();
		map.put("url","https://openapi.miaodiyun.com/distributor/sendSMS");
		map.put("accountSid","6998eca293f9ab9ae822a556dca9affc");
		map.put("authToken","d22b144c1de959e4d3c72fbcd9df8d27");
		map.put("phone",phone);
		Random random = new Random();
		String code = String.valueOf(random.nextInt(1000000));
		code = String.format("%0"+6+"d",Integer.parseInt(code));
		map.put("param",code);
		map.put("templateid","260369");
		System.out.println("验证码为:"+code);
		String data = SendCode.getSendCodeMessage(map);
		System.out.println(data);
		JSONObject json = JSON.parseObject(data);
		if (json.getString("respCode").equals("0000")){
			System.out.println("发送成功");
			subject.getSession().setAttribute(phone,code);
			subject.getSession().setAttribute("codeTime", new Date().getTime());
			return  ResultUtil.success("验证码发送成功");
		}else {
			System.out.println("验证码发送失败，失败原因为:" +json.getString("respDesc"));
			return ResultUtil.error("验证码发送失败");
		}


	}


	/**
	 * 个人中心
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/core")
	@ResponseBody
	public String core(@RequestBody JSONObject jsonObject){
		Member member = memberService.getCurrent();
		JSONObject return_json = new JSONObject();
		return_json.put("member",member);
		return ResultUtil.success(return_json);


	}








}
