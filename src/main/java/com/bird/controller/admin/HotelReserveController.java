package com.bird.controller.admin;

import com.bird.entity.ContacUs;
import com.bird.entity.HotelReserve;
import com.bird.entity.Meeting;
import com.bird.service.ContacUsService;
import com.bird.service.HotelReserveService;
import com.bird.service.MeetingService;
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

@Controller("adminHotelReserve")
@RequestMapping("/admin/hotelReserve")
public class HotelReserveController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HotelReserveService hotelReserveService;

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
		List<Map> mettings = hotelReserveService.getHotelReserveList(null);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		return "admin/hotelReserve/list";
	}

    /**
     * 编辑页面
     * @param model
     * @param meeting_id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long meeting_id) {
    	HotelReserve hotelReserve = hotelReserveService.selectByPrimaryKey(meeting_id);
		Meeting meeting = meetingService.selectByPrimaryKey(meeting_id);
		model.addAttribute("meeting",meeting);
		model.addAttribute("hotelReserve",hotelReserve);
        return "admin/hotelReserve/edit";
    }


	/**
     * 编辑
	 * @param model
     * @param hotelReserve
     * @return
     */
	@RequestMapping("/update")
	public String update(Model model,HotelReserve hotelReserve){
		hotelReserveService.updateByPrimaryKeySelective(hotelReserve);
		return  "redirect:list";
	}




}
