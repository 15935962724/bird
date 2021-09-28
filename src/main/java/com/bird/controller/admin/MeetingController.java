package com.bird.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.*;
import com.bird.service.*;
import com.bird.util.DateUtil;
import com.bird.util.FileUtils;
import com.bird.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.binding.BidirectionalBinding;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminMeeting")
@RequestMapping("/admin/meeting")
public class MeetingController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;


	@Autowired
	private MeetingService meetingService;

	@Autowired
    private AreaService areaService;

	@Autowired
	private ContacUsService contacUsService;

	@Autowired
	private HotelReserveService hotelReserveService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private RegisterPolicyService registerPolicyService;

	@Autowired
	private AttractService attractService;

	@Autowired
	private EpidemicService epidemicService;

	@Autowired
	private OrganizationalService organizationalService;

	@Autowired
	private TrafficService trafficService;

	@Autowired
	private PlatformTransactionManager txManager;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,
					   String title) {
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		Map query_map = new HashMap();
		query_map.put("title",title);
		List<Map> mettings = meetingService.getMeetings(query_map);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(mettings);
		model.addAttribute("page",pageInfo);
		model.addAttribute("title",title);
		return "admin/meeting/list";
	}

	/**
	 * 添加会议页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/meeting/add";
	}


	/**
	 * 添加会议
	 * @param model
	 * @param meeting
	 * @param meeting_logo
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model, Meeting meeting, String hold_date,
					   @RequestParam("meeting_logo") MultipartFile[] meeting_logo ){

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			StringBuffer logos = new StringBuffer();
			for (MultipartFile meetingLogo : meeting_logo) {
				if (meetingLogo.getSize()>0){
					String  logo = FileUtils.upload(meetingLogo,"/meeting_logo/");
					logos.append(logo).append(",");
				}
			}

			Date holdDate = DateUtil.getStringtoDate(hold_date,"yyyy-MM-dd");
//			String  logo = FileUtils.upload(meeting_logo,"/meeting_logo/");
			meeting.setHoldDate(holdDate);
			meeting.setCreateDate(new Date());
			meeting.setLogo(logos.deleteCharAt(logos.length() - 1).toString());
			meetingService.insertSelective(meeting);
			ContacUs contacUs = new ContacUs();
			contacUs.setMeetingId(meeting.getId());
			contacUs.setContent("");
			contacUsService.insertSelective(contacUs);
			HotelReserve hotelReserve = new HotelReserve();
			hotelReserve.setMeetingId(meeting.getId());
			hotelReserve.setContent("");
			hotelReserveService.insertSelective(hotelReserve);
			Notice notice = new Notice();
			notice.setContent("");
			notice.setMeetingId(meeting.getId());
			noticeService.insertSelective(notice);
			RegisterPolicy registerPolicy = new RegisterPolicy();
			registerPolicy.setContent("");
			registerPolicy.setMeetingId(meeting.getId());
			registerPolicyService.insertSelective(registerPolicy);
			Attract attract = new Attract();
			attract.setMeetingId(meeting.getId());
			attract.setContent("");
			attractService.insertSelective(attract);
			Epidemic epidemic = new Epidemic();
			epidemic.setMeetingId(meeting.getId());
			epidemic.setContent("");
			epidemicService.insertSelective(epidemic);
			Organizational organizational = new Organizational();
			organizational.setMeetingId(meeting.getId());
			organizational.setContent("");
			organizationalService.insertSelective(organizational);
			Traffic traffic = new Traffic();
			traffic.setMeetingId(meeting.getId());
			traffic.setContent("");
			trafficService.insertSelective(traffic);
			txManager.commit(status);
			return  "redirect:list";
		}catch (Exception e){
			System.out.println("事务回滚了");
			//强制手动事务回滚
			txManager.rollback(status);
			return  "redirect:list";
		}





	}


    /**
     * 编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long id) {
		Meeting meeting = meetingService.selectByPrimaryKey(id);
        model.addAttribute("meeting",meeting);
        return "admin/meeting/edit";
    }


	/**
	 * 编辑
	 * @param model
	 * @param meeting
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Meeting meeting,String hold_date,
						 @RequestParam("meeting_logo") MultipartFile[] meeting_logo){


		StringBuffer logos = new StringBuffer();
		for (MultipartFile meetingLogo : meeting_logo) {
			if (meetingLogo.getSize()>0){
				String  logo = FileUtils.upload(meetingLogo,"/meeting_logo/");
				logos.append(logo).append(",");
			}
		}

		Date holdDate = DateUtil.getStringtoDate(hold_date,"yyyy-MM-dd");
//			String  logo = FileUtils.upload(meeting_logo,"/meeting_logo/");
		meeting.setHoldDate(holdDate);
		meeting.setCreateDate(new Date());
		if (logos.length()>0){
			meeting.setLogo(logos.deleteCharAt(logos.length() - 1).toString());
		}
		meetingService.updateByPrimaryKeySelective(meeting);
		return  "redirect:list";
	}



	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestBody String param ){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			JSONObject jsonObject = (JSONObject) JSON.parse(param);
			Long id = jsonObject.getLong("id");

			contacUsService.deleteByPrimaryKey(id);
			hotelReserveService.deleteByPrimaryKey(id);
			noticeService.deleteByPrimaryKey(id);
			registerPolicyService.deleteByPrimaryKey(id);
			meetingService.deleteByPrimaryKey(id);
			return  ResultUtil.success();
		}catch (Exception e){
			System.out.println("事务回滚了");
			//强制手动事务回滚
			txManager.rollback(status);
			return  ResultUtil.error("操作失败!");
		}
	}



}
