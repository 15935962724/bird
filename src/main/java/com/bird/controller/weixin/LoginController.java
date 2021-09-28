package com.bird.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bird.config.AdminAuthenticationToken;
import com.bird.config.MemberAuthenticationToken;
import com.bird.config.ShiroConfig;
import com.bird.entity.Member;
import com.bird.service.MeetingService;
import com.bird.service.MemberService;
import com.bird.util.WeiXinUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller("weixinLogin")
public class LoginController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberService memberService;

	@Autowired
	private MeetingService meetingService;

	@Value("${wxAppid}")
	private String wxAppid;


	@Value("${wxSecret}")
	private String wxSecret;

	@RequestMapping("/wxLogin")
	public String index(Model model, String code, HttpServletRequest request) {
		System.out.println("当前请求路径:"+request.getRequestURI());
		Subject subject = SecurityUtils.getSubject();
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code",code);
		jsonObject.put("appId",wxAppid);
		jsonObject.put("secret",wxSecret);
		System.out.println("微信参数"+jsonObject);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WeiXinUtils wxu = new WeiXinUtils();
		JSONObject userinfojson = null;
		try {
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
			member.setWxLogo(jsonObject.getString("logo"));
			member.setNickName(jsonObject.getString("nickname"));
			member.setCreateDate(new Date());
			memberService.insertSelective(member);
		}
		UsernamePasswordToken token = new MemberAuthenticationToken(member.getOpenId());
		subject.login(token);
		subject.getSession().setAttribute("meeting",meetingService.getMeeting());
		model.addAttribute("userinfojson",userinfojson);//微信信息
		return "/weixin/index";
	}



	@RequestMapping("/wxSubmit")
	@ResponseBody
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
			return "redirect:/admin/index/"	;
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





}
