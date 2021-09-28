package com.bird.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.bird.entity.*;
import com.bird.service.*;
import com.bird.util.DateUtil;
import com.bird.util.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminContacUs")
@RequestMapping("/admin/contacUs")
public class ContacUsController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ContacUsService contacUsService;

	@Autowired
	private MeetingService meetingService;


	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum,
					   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		List<Map> mettings = contacUsService.getContacUsList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/contacUs/list";
	}

    /**
     * 编辑页面
     * @param model
     * @param meeting_id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long meeting_id) {
    	ContacUs contacUs = contacUsService.selectByPrimaryKey(meeting_id);
    	Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
        model.addAttribute("meeting",meeting);
        model.addAttribute("contacUs",contacUs);
        return "admin/contacUs/edit";
    }


	/**
	 * 编辑
	 * @param model
	 * @param contacUs
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, ContacUs contacUs){
		contacUsService.updateByPrimaryKeySelective(contacUs);
		return  "redirect:list";
	}




}
