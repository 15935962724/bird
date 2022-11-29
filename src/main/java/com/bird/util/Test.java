package com.bird.util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class Test {


	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, AesException {



//		String realName = "北京市,大兴区";
//		System.out.println(realName.lastIndexOf(","));
//		System.out.println(realName.substring(0,realName.lastIndexOf(",")));
//		String realName2 = "河北省,石家庄,人人区";
//		System.out.println(realName2.lastIndexOf(","));
//		System.out.println(realName2.substring(0,realName2.lastIndexOf(",")));
//

		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonce = String.valueOf( (Double) Class.forName("java.lang.Math").getMethod("ran" + "dom").invoke(null));
		String token = "tokenBird2021";
		String encrypt = "7YfMWKZCJWCL0LUaU556agmpyCIPkjLbGzjVEMSsZsK";
		// 签名
//		"7YfMWKZCJWCL0LUaU556agmpyCIPkjLbGzjVEMSsZsK";
		String aa  = SHA1.getSHA1(token,timestamp,nonce,encrypt);
		System.out.println(aa);


		// 需要加密的明文
		String encodingAesKey = "7YfMWKZCJWCL0LUaU556agmpyCIPkjLbGzjVEMSsZsK";
//		String token = "pamtest";
//		String timestamp = "1409304348";
//		String nonce = "xxxxxx";
		String appId = "wx216689b717dbc269";
		String secret = "39c409942dd0c7217a0ce1fba59e90b3";

		String replyMsg = "<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName>" +
				"<FromUserName><![CDATA[gh_7f083739789a]]></FromUserName>" +
				"<CreateTime>1407743423</CreateTime>" +
				"<MsgType><![CDATA[video]]></MsgType>" +
				"<Video>" +
					"<MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId>" +
					"<Title><![CDATA[testCallBackReplyVideo]]></Title>" +
					"<Description><![CDATA[testCallBackReplyVideo]]></Description>" +
				"</Video></xml>";
		replyMsg = "<xml>\n" + "<Encrypt><![CDATA[" + encrypt + "]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[" + aa
				+ "]]></MsgSignature>\n" + "<TimeStamp>" + timestamp
				+ "</TimeStamp>\n" + "<Nonce><![CDATA[" + nonce
				+ "]]></Nonce>\n" + "</xml>";

//		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
//		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
//		System.out.println("加密后: " + mingwen);

		JSONObject wx = new JSONObject();
		wx.put("appId",appId);
		wx.put("secret",secret);
		String access_token = WeiXinUtils.getAccess_token(wx);
		String word = createWord(access_token);
		System.out.println(word);
		String words = getWords(access_token);
		System.out.println(words);


	}


	/**
	 * 添加文字素材
	 * @param access_token
	 * @return
	 */
	public static String createWord(String access_token){
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+access_token;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type",0);
		jsonObject.put("word","近距离治疗、放疗手术机器人及人工智能/放疗云技术室近年来放疗应用发展最为快速的领域，质子/重离子及中子俘获等高端设备正在从研发向临床应用领域推广。分会将持续致力于实现以学术为导向，核技术应用为目标，先进放疗技术产品/解决方案为引擎，临床应用实践为核心价值，基于人工智能、大数据、云计算及互联网技术，搭建创新应用服务平台，营造从基层到高端医疗机构的全产业生态链，成为备受尊重的行业引领者。");
		String data = HttpUtil.post(url,jsonObject.toString());
		return data;
	}


	/**
	 * 查询文字素材
	 * @param access_token
	 * @return
	 */
	public static String getWords(String access_token){
		String url = "https://api.weixin.qq.com/cgi-bin/guide/getguidewordmaterial?access_token="+access_token;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type",0);
		jsonObject.put("start",0);
		jsonObject.put("num",10);
		String data = HttpUtil.post(url,jsonObject.toString());
		return data;
	}

	public static String push(String access_token){
		String xml = "<xml>\n" +
				" <ToUserName><![CDATA[wx216689b717dbc269]]></ToUserName>\n" +
				" <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
				" <CreateTime>"+System.currentTimeMillis()+"</CreateTime>\n" +
				" <MsgType><![CDATA[text]]></MsgType>\n" +
				" <Content><![CDATA[this is a test]]></Content>\n" +
				" <MsgId>1234567890123456</MsgId>\n" +
				" </xml>";
		return xml;
	}











}
