package com.bird.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.bird.entity.Attract;
import com.bird.entity.ContacUs;
import com.bird.entity.Meeting;
import com.bird.service.AttractService;
import com.bird.service.MeetingService;
import com.bird.service.ScheduleService;
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

@Controller("adminAttract")
@RequestMapping("/admin/attract")
public class AttractController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AttractService attractService;

	@Autowired
	private MeetingService meetingService;

	@Value("${meetingId}")
	private Long meetingId;


	/**
	 * 会议招商
	 * @param
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum,
					   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		List<Map> mettings = attractService.getAttractList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/attract/list";
	}

	/**
	 * 编辑页面
	 * @param model
	 * @param meeting_id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model,Long meeting_id) {
		Attract attract = attractService.selectByPrimaryKey(meeting_id);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("attract",attract);
		return "admin/attract/edit";
	}


	/**
	 * 编辑
	 * @param model
	 * @param attract
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Attract attract){
		attractService.updateByPrimaryKeySelective(attract);
		return  "redirect:list";
	}






}
