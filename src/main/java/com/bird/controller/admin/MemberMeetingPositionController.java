package com.bird.controller.admin;

import com.bird.entity.MemberMeetingPosition;
import com.bird.service.MeetingService;
import com.bird.service.MemberMeetingPositionService;
import com.bird.service.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminMemberMeetingPosition")
@RequestMapping("/admin/memberMeetingPosition")
public class MemberMeetingPositionController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private MemberMeetingPositionService memberMeetingPositionService;

	@Autowired
	private MemberService memberService;

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,
					  String title,String name,String position) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		Map query_map = new HashMap();
		query_map.put("title",title);
		query_map.put("name",name);
		query_map.put("position",position);
		List<Map> memberMeetingPositions = memberMeetingPositionService.getMemberMeetingPositions(query_map);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(memberMeetingPositions);
		model.addAttribute("page",pageInfo);
		model.addAttribute("title",title);
		model.addAttribute("name",name);
		model.addAttribute("position",position);
		List<Map> mettings = meetingService.getMeetings(null);
		model.addAttribute("mettings",mettings);
		List<Map> members = memberService.getMembers(null);
		model.addAttribute("members",members);
		return "admin/memberMeetingPosition/list";
	}

	/**
	 * 添加职务页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<Map> mettings = meetingService.getMeetings(null);
		model.addAttribute("mettings",mettings);
		List<Map> members = memberService.getMembers(null);
		model.addAttribute("members",members);
		return "admin/memberMeetingPosition/add";
	}


	/**
	 * 添加职务
	 * @param model
	 * @param memberMeetingPosition
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model, MemberMeetingPosition memberMeetingPosition){
			memberMeetingPositionService.insertSelective(memberMeetingPosition);
			return  "redirect:list";
	}




}
