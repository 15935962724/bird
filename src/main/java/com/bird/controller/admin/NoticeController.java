package com.bird.controller.admin;

import com.bird.entity.Meeting;
import com.bird.entity.Member;
import com.bird.entity.Notice;
import com.bird.service.MeetingService;
import com.bird.service.NoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
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

@Controller("adminNotice")
@RequestMapping("/admin/notice")
public class NoticeController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private NoticeService noticeService;

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
		List<Map> mettings = noticeService.getNoticeList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/notice/list";
	}


//    @RequestMapping("/recycle_bin")
//    public String recycle_bin() {
//        return "admin/notice/recycle_bin";
//    }


	@RequestMapping("/edit")
	public String edit(Model model,Long meeting_id) {
		Notice notice = noticeService.selectByPrimaryKey(meeting_id);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("notice",notice);
		return "admin/notice/edit";
	}


	/**
     * 编辑
	 * @param model
     * @param notice
     * @return
     */
	@RequestMapping("/update")
	public String update(Model model,Notice notice){
		noticeService.updateByPrimaryKeySelective(notice);
		return  "redirect:list";
	}




}
