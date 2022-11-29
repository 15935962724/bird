package com.bird.service.impl;


import com.bird.dao.AdminMapper;
import com.bird.entity.Admin;
import com.bird.entity.Article;
import com.bird.entity.NewsMessage;
import com.bird.entity.TextMessage;
import com.bird.service.AdminService;
import com.bird.service.CoreService;
import com.bird.util.WeixinMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("coreService")
public class CoreServiceImpl implements CoreService {

	private static Logger log = LoggerFactory.getLogger(CoreServiceImpl.class);

	@Autowired
	private WeixinMessageUtil weixinMessageUtil;

	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = weixinMessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			log.info("open_id:"+fromUserName);
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(weixinMessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);


			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(weixinMessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();


			//点击菜单id
			String EventKey =requestMap.get("EventKey");
			// 接收文本消息内容
			String content = requestMap.get("Content");
			// 自动回复文本消息
			if (msgType.equals(weixinMessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				//如果用户发送表情，则回复同样表情。
				if (isQqFace(content)) {
					respContent = content;
					textMessage.setContent(respContent);
					// 将文本消息对象转换成xml字符串
					respMessage = weixinMessageUtil.textMessageToXml(textMessage);
				}
			}



			// 事件推送
			else if (msgType.equals(weixinMessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType =requestMap.get("Event");
				// 自定义菜单点击事件
				if (eventType.equals(weixinMessageUtil.EVENT_TYPE_CLICK)) {
					System.out.println(EventKey);
					switch (EventKey){
						case "11":{

							respContent="近距离治疗、放疗手术机器人及人工智能/放疗云技术室近年来放疗应用发展最为快速的领域，质子/重离子及中子俘获等高端设备正在从研发向临床应用领域推广。分会将持续致力于实现以学术为导向，核技术应用为目标，先进放疗技术产品/解决方案为引擎，临床应用实践为核心价值，基于人工智能、大数据、云计算及互联网技术，搭建创新应用服务平台，营造从基层到高端医疗机构的全产业生态链，成为备受尊重的行业引领者。";
							textMessage.setContent(respContent);

							respMessage=weixinMessageUtil.textMessageToXml(textMessage).replace(" ","");
							System.out.println("respContent:"+respContent);
							return respMessage;
//																		Article article = new Article();
//																		article.setTitle("嘉创软件平台！");
//																		// 图文消息中可以使用QQ表情、符号表情
//																		article.setDescription("点击前往个人中心");
//																		// 将图片置为空
//																		article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578996280210&di=748792df7f36755189d8924fd2d0ca1d&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Frushidao%2Fpics%2Fhv1%2F20%2F108%2F1744%2F113431160.jpg");
//																		article.setUrl("http://www.xiao001.fun/saas/webcat/index.html?url=11&openId="+fromUserName);
//																		articleList.add(article);
//																		newsMessage.setArticleCount(articleList.size());
//																		newsMessage.setArticles(articleList);
//                            respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);

//                            respContent="<a href=\"http://192.168.0.106:8080/saas/webcat?url=11&openId="+fromUserName+"\">"+"点击前往个人中心"+"</a>";
						}
						case "12":{
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("点击前往支部信息");
							// 将图片置为空
							article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578996280210&di=748792df7f36755189d8924fd2d0ca1d&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Frushidao%2Fpics%2Fhv1%2F20%2F108%2F1744%2F113431160.jpg");
							article.setUrl("http://www.xiao001.fun/saas/webcat/index.html?url=12&openId="+fromUserName);
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
//                            respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);


//                            respContent="<a href=\"http://192.168.0.106:8080/saas/webcat?url=12&openId="+fromUserName+"\">"+"点击前往支部信息"+"</a>";
							break;
						}
						case "13":{
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("点击前往党费缴纳");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl("");
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);

//                            respContent = "党费缴纳";
							break;
						}
						case "14":{
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("点击前往材料报送");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl("");
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);

//                            respContent = "材料报送";
							break;
						}
						case "15":{
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("点击前往入党详情");
							// 将图片置为空
							article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578996280210&di=748792df7f36755189d8924fd2d0ca1d&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Frushidao%2Fpics%2Fhv1%2F20%2F108%2F1744%2F113431160.jpg");
							article.setUrl("http://www.xiao001.fun/saas/webcat/index.html?url=15&openId="+fromUserName);
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
//                            respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);


//                            respContent="<a href=\"http://192.168.0.106:8080/saas/webcat?url=15&openId="+fromUserName+"\">"+"点击前往入党详情"+"</a>";
							break;
						}
						case "22":{
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("点击前往会议活动");
							// 将图片置为空
							article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578996280210&di=748792df7f36755189d8924fd2d0ca1d&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Frushidao%2Fpics%2Fhv1%2F20%2F108%2F1744%2F113431160.jpg");
							article.setUrl("http://www.xiao001.fun/saas/webcat/index.html?url=22&openId="+fromUserName);
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
//                            respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);


//                            respContent="<a href=\"http://192.168.0.106:8080/saas/webcat?url=22&openId="+fromUserName+"\">"+"点击前往会议活动"+"</a>";
							break;
						}
						/**
						 * 这里在自定义菜单时，改为view事件类型，直接跳转
						 */
						default:{
							log.error("开发者反馈：EventKey值没找到，它是:"+EventKey);
							Article article = new Article();
							article.setTitle("嘉创软件平台！");
							// 图文消息中可以使用QQ表情、符号表情
							article.setDescription("很抱歉，此按键功能正在升级无法使用");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl("");
							articleList.add(article);
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
//                            respContent= "很抱歉，此按键功能正在升级无法使用";
						}
					}
//                    textMessage.setContent(respContent);
//                    // 将文本消息对象转换成xml字符串
//                    respMessage = weixinMessageUtil.textMessageToXml(textMessage);
					/**
					 * 对图文消息对象进行转换成xml字符串
					 */
					respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);
				}
				else if(eventType.equals(weixinMessageUtil.EVENT_TYPE_VIEW)){
					// 对于点击菜单转网页暂时不做推送

				}

				// 订阅
				else if (eventType.equals(weixinMessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//                    //测试单图文回复
//                    Article article = new Article();
//                    article.setTitle("谢谢您的关注！");
//                    // 图文消息中可以使用QQ表情、符号表情
//                    article.setDescription("点击图文可以跳转到百度首页");
//                    // 将图片置为空
//                    article.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
//                    article.setUrl("http://www.baidu.com");
//                    articleList.add(article);
//                    newsMessage.setArticleCount(articleList.size());
//                    newsMessage.setArticles(articleList);
//                    respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);
					/**
					 * 我新加的
					 */
					respContent="谢谢您的关注！";
					textMessage.setContent(respContent);
					respMessage=weixinMessageUtil.textMessageToXml(textMessage);
				}
//                else if(eventType.equals(weixinMessageUtil.EVENT_TYPE_SCAN)){
//                    //测试单图文回复
//                    Article article = new Article();
//                    article.setTitle("这是已关注用户扫描二维码弹到的界面");
//                    // 图文消息中可以使用QQ表情、符号表情
//                    article.setDescription("点击图文可以跳转到百度首页");
//                    // 将图片置为空
//                    article.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
//                    article.setUrl("http://www.baidu.com");
//                    articleList.add(article);
//                    newsMessage.setArticleCount(articleList.size());
//                    newsMessage.setArticles(articleList);
//                    respMessage = weixinMessageUtil.newsMessageToXml(newsMessage);
//                }
				// 取消订阅
				else if (eventType.equals(weixinMessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					log.info("取消订阅");
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("respMessage:"+respMessage);
		return respMessage;
	}



	/**
	 * 判断是否是QQ表情
	 *
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

}
