package com.bird.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bird.entity.Meeting;
import com.bird.entity.WeChatMenu;
import com.bird.service.MeetingService;
import com.bird.util.HttpUtil;
import com.bird.util.SettingUtils;
import com.bird.util.WeiXinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("adminWeChat")
@RequestMapping("/admin/weChat")
public class WeChatController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MeetingService meetingService;

	@Value("${wxAppid}")
	private String wxAppId;

	@Value("${wxSecret}")
	private String wxSecret;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		JSONObject wx = new JSONObject();
		wx.put("appId",wxAppId);
		wx.put("secret",wxSecret);
//		String access_token = WeiXinUtils.getAccess_token(wx);
		String access_token = SettingUtils.get().getAccessToken();
		String wxMenuSUrl = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token="+access_token;
		String wxMenus = HttpUtil.getInvoke(wxMenuSUrl);
		JSONObject wxMenusJson = JSON.parseObject(wxMenus);
		String selfmenu_info = wxMenusJson.getString("selfmenu_info");
		JSONObject jsonObject1 = JSON.parseObject(selfmenu_info);
		JSONArray jsonArray = jsonObject1.getJSONArray("button");
		List list = JSONArray.toJavaObject(jsonArray, List.class);
		model.addAttribute("list",list);
		model.addAttribute("wxMenus",wxMenus);
		return "admin/weChat/list";
	}


	/**
	 * 添加会议页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/weChat/add";
	}



	@RequestMapping("/delete")
	public String delete(Model model) {

		JSONObject wx = new JSONObject();
		wx.put("appId",wxAppId);
		wx.put("secret",wxSecret);
//		String access_token = WeiXinUtils.getAccess_token(wx);
		String access_token = SettingUtils.get().getAccessToken();
		String deleteWeChatMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+access_token;
		String invoke = HttpUtil.getInvoke(deleteWeChatMenuUrl);
		System.out.println("删除微信菜单结果:"+invoke);
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
		Meeting meeting = meetingService.selectByPrimaryKey(id);
        model.addAttribute("meeting",meeting);
        return "admin/meeting/edit";
    }


	/**
	 * 创建微信菜单
	 * @param model
	 * @param weChatMenu
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model,WeChatMenu weChatMenu){

		JSONObject jsonObject = new JSONObject();
//		JSONArray  jsonArray = new JSONArray();
		JSONObject wx = new JSONObject();
		wx.put("appId",wxAppId);
		wx.put("secret",wxSecret);
//		String access_token = WeiXinUtils.getAccess_token(wx);
		String access_token = SettingUtils.get().getAccessToken();
		String wxMenuSUrl = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token="+access_token;
		String wxMenus = HttpUtil.getInvoke(wxMenuSUrl);
		JSONObject wxMenusJson = JSON.parseObject(wxMenus);
		String selfmenu_info = wxMenusJson.getString("selfmenu_info");
		JSONObject jsonObject1 = JSON.parseObject(selfmenu_info);
		JSONArray jsonArray = jsonObject1.getJSONArray("button");

		if (weChatMenu.getParentId()==0){
			jsonObject.put("name",weChatMenu.getName());
			jsonObject.put("type",weChatMenu.getType());
			if (weChatMenu.getType().equals("view")){ //网页类型
				jsonObject.put("url",weChatMenu.getContent());
			}
			if (weChatMenu.getType().equals("click")){
				jsonObject.put("key",weChatMenu.getContent());

			}
			if (weChatMenu.getType().equals("miniprogram")){

			}

			jsonArray.add(jsonObject);
				JSONObject weChatMenus = new JSONObject();
				weChatMenus.put("button",jsonArray);
//			JSONObject wx = new JSONObject();
//			wx.put("appId",wxAppId);
//			wx.put("secret",wxSecret);
//			String access_token = WeiXinUtils.getAccess_token(wx);
			String createWxMenuUrl = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
			System.out.println(weChatMenus.toString());
			String data = HttpUtil.post(createWxMenuUrl, weChatMenus.toString());
			System.out.println(data);
			return  "redirect:list";
		}else {
			return  "redirect:list";
		}

	}



}
