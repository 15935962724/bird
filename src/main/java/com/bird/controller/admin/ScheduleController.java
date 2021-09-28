package com.bird.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.*;
import com.bird.service.*;
import com.bird.util.DateUtil;
import com.bird.util.FileUtils;
import com.bird.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminschedule")
@RequestMapping("/admin/schedule")
public class ScheduleController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;

	@Autowired
	private ScheduleService scheduleService;


    /**
     * 编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long id) {

    	model.addAttribute("meetingId",id);
    	Map query_map = new HashMap();
    	query_map.put("meetingId",id);
		List<Map> schedules = scheduleService.getSchedules(query_map);
		model.addAttribute("schedules",schedules);
		return "admin/schedule/edit";
    }



	@RequestMapping("/save")
	@ResponseBody
	public String save(@RequestBody String param ){
		JSONObject jsonObject = (JSONObject) JSON.parse(param);
		Schedule schedule  = JSON.parseObject(jsonObject.toJSONString(),Schedule.class);
		int i = scheduleService.insertSelective(schedule);
		return i>0?ResultUtil.success():ResultUtil.error("操作失败!");
	}


	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestBody String param ){
		JSONObject jsonObject = (JSONObject) JSON.parse(param);
		Schedule schedule  = JSON.parseObject(jsonObject.toJSONString(),Schedule.class);
		int i = scheduleService.updateByPrimaryKeySelective(schedule);
		return i>0?ResultUtil.success():ResultUtil.error("操作失败!");
	}



	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestBody String  param ){
		JSONObject jsonObject = (JSONObject) JSON.parse(param);
		int i = scheduleService.deleteByPrimaryKey(jsonObject.getLong("id"));
		return i>0?ResultUtil.success():ResultUtil.error("操作失败!");
	}



}
