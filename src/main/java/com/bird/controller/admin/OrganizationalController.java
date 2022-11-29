package com.bird.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.bird.entity.Epidemic;
import com.bird.entity.Meeting;
import com.bird.entity.Organizational;
import com.bird.service.MeetingService;
import com.bird.service.OrganizationalService;
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

@Controller("adminOrganizational")
@RequestMapping("/admin/organizational")
public class OrganizationalController {


	Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private MeetingService meetingService;

	@Autowired
	private OrganizationalService organizationalService;

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
		List<Map> mettings = organizationalService.getOrganizationalList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/organizational/list";
	}

	/**
	 * 编辑页面
	 * @param model
	 * @param meeting_id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model,Long meeting_id) {
		Organizational organizational = organizationalService.selectByPrimaryKey(meeting_id);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("organizational",organizational);
		return "admin/organizational/edit";
	}


	/**
	 * 编辑
	 * @param model
	 * @param organizational
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model,  Organizational organizational){
		organizationalService.updateByPrimaryKeySelective(organizational);
		return  "redirect:list";
	}






}
