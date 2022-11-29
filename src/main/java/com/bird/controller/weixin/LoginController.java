package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.config.MemberAuthenticationToken;
import com.bird.config.ShiroConfig;
import com.bird.entity.Member;
import com.bird.service.CoreService;
import com.bird.service.MeetingService;
import com.bird.service.MemberService;
import com.bird.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("weixinLogin")
public class LoginController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberService memberService;

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private CoreService coreService;

	@Value("${wxAppid}")
	private String wxAppid;


	@Value("${wxSecret}")
	private String wxSecret;

	@RequestMapping("/wxLogin")
	public String index(Model model, String code, HttpServletRequest request) throws IOException {

		System.out.println("当前请求路径:"+request.getRequestURI());
		Subject subject = SecurityUtils.getSubject();
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code",code);
		jsonObject.put("appId","wxc4f84cdd9ad8e0c8");
		jsonObject.put("secret","a304a798f950cc5206019aee1f21dc1b");
		System.out.println("微信参数"+jsonObject);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WeiXinUtils wxu = new WeiXinUtils();
		JSONObject userinfojson = null;
		try {
//			String access_token = WeiXinUtils.getAccess_token(jsonObject);
//
//			String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+jsontoken.getString("access_token")+"&openid="+ jsontoken.getString("openid") +"&lang=zh_CN";
//			String newstr = HttpUtil.getInvoke(url);
//			String userinfo = newstr.replace("\"", "'");

			userinfojson = JSON.parseObject(wxu.getUserInfo(jsonObject));
		} catch (IOException e) {
			System.out.println("获取微信信息出错!");
			e.printStackTrace();
		}
		System.out.println(userinfojson);
		String openid = userinfojson.getString("openid");
		System.out.println("获取登录人--nickName>>>"+userinfojson.get("nickname").toString()+"---访问时间>>>" + sdf.format(new Date()));
		Map<String,Object> map=new HashMap<>();
		map.put("openId",openid);
		Member member = memberService.getMember(map);
		if (member==null){
			member = new Member();
			member.setType(1l);
			member.setWxLogo(userinfojson.getString("headimgurl"));
			member.setNickName(userinfojson.getString("nickname"));
			member.setCreateDate(new Date());
			member.setOpenId(openid);
			member.setIsPayment(false);
			memberService.insertSelective(member);
		}
		UsernamePasswordToken token = new MemberAuthenticationToken(member.getOpenId());
		subject.login(token);
		subject.getSession().setAttribute("meeting",meetingService.getMeeting());
		model.addAttribute("userinfojson",userinfojson);//微信信息
		System.out.println("准备进入weixin/index");
		return "/weixin/index";
	}

	@RequestMapping("/wxIndex")
	public String wxIndex(Model model, HttpServletRequest request) throws IOException {
		return "/weixin/index";
	}





	@RequestMapping("/wxSubmit")
	public String wxSubmit(Model model, String code,HttpServletRequest request) {
		System.out.println("当前请求路径:"+request.getRequestURI());
		Subject subject = SecurityUtils.getSubject();
		String openid = "1111111111";
		Map<String,Object> map=new HashMap<>();
		map.put("openId",openid);
		Member member = memberService.getMember(map);

			String mwpassword = "111111";
			String encodedPassword = ShiroConfig.shiroEncryption(mwpassword,openid);
			System.out.println("密码："+encodedPassword);

		if (member==null){
			member = new Member();
			member.setType(1l);
//			member.setWxLogo(jsonObject.getString("logo"));
//			member.setNickName(jsonObject.getString("nickname"));
			member.setCreateDate(new Date());
			memberService.insertSelective(member);
		}

		try {
			UsernamePasswordToken token = new MemberAuthenticationToken(member.getOpenId());
			subject.login(token);
			subject.getSession().setAttribute("meeting",meetingService.getMeeting());
			return "redirect:/weixin/index/"	;
		} catch (IncorrectCredentialsException e) {
			logger.info("密码错误");
			map.put("msg"	,"密码错误");
		} catch (LockedAccountException e) {
			map.put("msg"	,"该用户已被冻结");
			logger.info("该用户已被冻结");
		} catch (AuthenticationException e) {
			logger.info("该用户不存在");
			map.put("msg"	,"该用户不存在");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("登录错误");
			map.put("msg"	,"登录错误");
		}
		return "/weixin/index";
	}



	@RequestMapping(value = "/checkSignature",method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String checkSignatureGet(@RequestParam(value = "signature", required = false) String signature,
								  @RequestParam(value = "timestamp", required = false) String timestamp,
								  @RequestParam(value = "nonce", required = false) String nonce,
								  @RequestParam(value = "echostr",required=false) String echostr){

									System.out.println(signature+","+timestamp+","+nonce+","+echostr);


		String token = "tokenBird2021";
		// 需要加密的明文
		String encrypt =echostr;

		String[] arr = {token,timestamp,nonce};
		StringBuffer sb = new StringBuffer();
		Arrays.sort(arr);
		for(int i = 0;i < arr.length;i++) {
			sb.append(arr[i]);
		}
		String aa  = SHA1.SHA1(sb.toString());

		System.out.println("boolean:"+aa.equals(signature));
		if (aa.equals(signature)){
			return echostr;

		}
		return null;

}





	@RequestMapping(value = "/checkSignature",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String checkSignaturePost(HttpServletRequest req) {
		System.out.println("进入post");
		String respMessage = coreService.processRequest(req);
		System.out.println(respMessage);
		return respMessage;

	}





}
