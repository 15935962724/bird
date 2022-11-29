package com.bird.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class WeChatTest {


	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, AesException {

		String appId = "wx216689b717dbc269";
		String secret = "39c409942dd0c7217a0ce1fba59e90b3";

		JSONObject wx = new JSONObject();
		wx.put("appId",appId);
		wx.put("secret",secret);
		String access_token = WeiXinUtils.getAccess_token(wx);


		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

			jsonObject.put("name","关于我们");
			jsonObject.put("type","click");
			jsonObject.put("key","11");


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




	}





}
