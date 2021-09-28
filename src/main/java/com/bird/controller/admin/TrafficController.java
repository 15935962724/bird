package com.bird.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.bird.entity.Meeting;
import com.bird.entity.Organizational;
import com.bird.entity.Traffic;
import com.bird.service.MeetingService;
import com.bird.service.ScheduleService;
import com.bird.service.TrafficService;
import com.bird.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminTraffic")
@RequestMapping("/admin/traffic")
public class TrafficController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private TrafficService trafficService;

	@Value("${meetingId}")
	private Long meetingId;


	/**
	 * 会场交通
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
					   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		List<Map> mettings = trafficService.getTrafficList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/traffic/list";
	}

	/**
	 * 编辑页面
	 * @param model
	 * @param meeting_id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model,Long meeting_id) {
		Traffic traffic = trafficService.selectByPrimaryKey(meetingId);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("traffic",traffic);
		return "admin/traffic/edit";
	}


	/**
	 * 编辑
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model,Traffic traffic){
		trafficService.updateByPrimaryKeySelective(traffic);
		return  "redirect:list";
	}







}
