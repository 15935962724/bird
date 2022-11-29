package com.bird.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.Area;
import com.bird.entity.Member;
import com.bird.service.AdminService;
import com.bird.service.AreaService;
import com.bird.service.MemberService;
import com.bird.util.FileUtils;
import com.bird.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller("adminMember")
@RequestMapping("/admin/member")
public class MemberController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;

	@Autowired
	private MemberService memberService;

	@Autowired
    private AreaService areaService;

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,
					   String name,String phone,Long type) {
		Map query_map = new HashMap();
		query_map.put("name",name);
		query_map.put("phone",phone);
		query_map.put("type",type);
		Page<Map<String, Object>> page = PageHelper.startPage(pageNum, pageSize);
		List<Map> members = memberService.getMembers(query_map);
		PageInfo<Map> pageInfo = new PageInfo<Map>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(members);
		model.addAttribute("page",pageInfo);
		model.addAttribute("name",name);
		model.addAttribute("phone",phone);
		model.addAttribute("type",type);
		return "admin/member/list";
	}

	/**
	 * 添加会员页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/member/add";
	}


	/**
	 * 添加会员
	 * @param model
	 * @param member
	 * @param member_logo
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model, Member member,@RequestParam("member_logo") MultipartFile member_logo){
			String logo = FileUtils.upload(member_logo,"/member_logo/");
			member.setCreateDate(new Date());
			member.setLogo(logo);
			member.setType(2L);
			member.setIsPayment(false);
			memberService.insertSelective(member);
			return  "redirect:list";
	}


    /**
     * 编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,Long id) {
        Member member = memberService.selectByPrimaryKey(id);
        String tree_path = ",";
        if (member.getAreaId()!=null&&member.getAreaId()!=""){
            Area area = areaService.selectByPrimaryKey(Long.valueOf(member.getAreaId()));
            tree_path = area.getTreePath();
        }
        model.addAttribute("tree_path",tree_path);
        model.addAttribute("member",member);
        return "admin/member/edit";
    }

	/**
	 * 查看
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String view(Model model,Long id) {
		Member member = memberService.selectByPrimaryKey(id);
		String tree_path = ",";
		if (member.getAreaId()!=null){
			Area area = areaService.selectByPrimaryKey(Long.valueOf(member.getAreaId()));
			tree_path = area.getTreePath();
		}
		model.addAttribute("tree_path",tree_path);
		model.addAttribute("member",member);
		return "admin/member/view";
	}


	/**
	 * 编辑
	 * @param model
	 * @param member
	 * @param member_logo
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Member member,@RequestParam("member_logo") MultipartFile member_logo){
		if (member_logo.getSize()>0){
			String logo = FileUtils.upload(member_logo,"/member_logo/");
			member.setLogo(logo);
		}
		memberService.updateByPrimaryKeySelective(member);
		return  "redirect:list";
	}


	@RequestMapping("/updatePayment")
	@ResponseBody
	public String updatePayment(@RequestBody String params ){
		JSONObject jsonObject = JSONObject.parseObject(params);
		Member member = JSONObject.parseObject(params,Member.class);
		int i = memberService.updateByPrimaryKeySelective(member);
		return i>0? ResultUtil.success() :ResultUtil.error("操作失败!");
	}





}
