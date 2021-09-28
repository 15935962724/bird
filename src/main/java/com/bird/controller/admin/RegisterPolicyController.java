package com.bird.controller.admin;

import com.bird.entity.Meeting;
import com.bird.entity.Notice;
import com.bird.entity.RegisterPolicy;
import com.bird.service.MeetingService;
import com.bird.service.NoticeService;
import com.bird.service.RegisterPolicyService;
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

import java.util.List;
import java.util.Map;

@Controller("adminRegisterPolicy")
@RequestMapping("/admin/registerPolicy")
public class RegisterPolicyController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RegisterPolicyService registerPolicyService;

	@Autowired
	private MeetingService meetingService;

	/**
	 *
	 * @param model
	 * @return
	 */	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum,
					   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		List<Map> mettings = registerPolicyService.getRegisterPolicyList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/registerPolicy/list";
	}

    /**
     * 编辑页面
     * @param model
     * @param meeting_id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long meeting_id) {
		RegisterPolicy registerPolicy = registerPolicyService.selectByPrimaryKey(meeting_id);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("registerPolicy",registerPolicy);
        return "admin/registerPolicy/edit";
    }


	/**
     * 编辑
	 * @param model
     * @param notice
     * @return
     */
	@RequestMapping("/update")
	public String update(Model model,RegisterPolicy registerPolicy){
		registerPolicyService.updateByPrimaryKeySelective(registerPolicy);
		return  "redirect:list";
	}




}
