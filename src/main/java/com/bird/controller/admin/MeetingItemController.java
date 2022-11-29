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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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

@Controller("adminMeetingItem")
@RequestMapping("/admin/meetingItem")
public class MeetingItemController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingItemServive meetingItemServive;

	@Autowired
	private PlatformTransactionManager txManager;

	/**分会场
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,
					   Long meetingId) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		Map query_map = new HashMap();
		query_map.put("meetingId",meetingId);
		List<Map> meetingItems = meetingItemServive.getMeetingItems(query_map);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(meetingItems);
		model.addAttribute("page",pageInfo);
		model.addAttribute("meetingId",meetingId);
		return "admin/meetingItem/list";
	}


	/**
	 * 保存分会场
	 * @param param
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@RequestBody String param ){
		MeetingItem meetingItem = JSON.parseObject(param, MeetingItem.class);
		int i = meetingItemServive.insertSelective(meetingItem);
		return i>0?ResultUtil.success():ResultUtil.error("添加失败！");

	}

	/**
	 * 修改分会场名称
	 * @param param
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestBody String param ){
		MeetingItem meetingItem = JSON.parseObject(param, MeetingItem.class);
		int i = meetingItemServive.updateByPrimaryKeySelective(meetingItem);
		return i>0?ResultUtil.success():ResultUtil.error("添加失败！");

	}





}
