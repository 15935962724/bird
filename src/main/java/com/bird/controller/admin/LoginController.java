package com.bird.controller.admin;

import com.bird.config.AdminAuthenticationToken;
import com.bird.service.AdminService;
import com.bird.util.ImageCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller("adminlogin")
public class LoginController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;


	@PostMapping(value = "/login")
    public String list(@RequestParam("username")String username,
					   @RequestParam("password") String password,
					   @RequestParam("code") String code,
					   Map<String,Object> map) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String verifyCode = (String) subject.getSession().getAttribute("strEnsure");
			if (null==verifyCode) {
				map.put("msg"	,"验证码已失效");
				return "admin/login";
			}
			if (!code.equals(verifyCode)) {
				map.put("msg"	,"验证码输入有误");
				return "admin/login";
			}
			UsernamePasswordToken adminAuthenticationToken = new AdminAuthenticationToken(username,password);
//			String mwpassword = username.equals("admin")?username:"111111";
//			String encodedPassword = ShiroConfig.shiroEncryption(mwpassword,username);
//			System.out.println("密码："+encodedPassword);
			//进行验证，这里可以捕获异常，然后返回对应信息
			subject.login(adminAuthenticationToken);
			UUID uuid = UUID.randomUUID();
//			// 把用户登录信息存入缓存 key值为 TOKEN_{用户标识}
//			ehcacheUtil.put(EhCacheConstants.TOKEN_PREFIX + uuid.toString(), token);
			subject.getSession().setAttribute("loginUser",username);
			subject.getSession().setAttribute("loginDate",new Date());
			String redirect_url = (String) subject.getSession().getAttribute("adminRedirectUrl");
			if (redirect_url!=null&&!redirect_url.equals("")){
				return "redirect:"+	redirect_url;
			}
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
		return "admin/login";
    }


	/**
	 * 退出登陆
	 * @return
	 */
	@RequestMapping(value = "/lognout")
	public String lognout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "admin/login";
	}

	/**
	 * 管理员登录页
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		logger.info("管理员登录页");
		return "admin/login";
	}


	/**
	 * 获取验证码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCodeImage")
	public void getCodeImage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer IMG_HEIGHT = 36;
		Integer IMG_WIDTH = 85;
		Map<String, Object> imageCode = ImageCodeUtil.imageCode(IMG_WIDTH,IMG_HEIGHT,4);
		Subject subject = SecurityUtils.getSubject();
		System.out.println("验证码为："+imageCode.get("strEnsure"));
		subject.getSession().setAttribute("strEnsure",  imageCode.get("strEnsure"));
		subject.getSession().setAttribute("codeTime", new Date().getTime());
		ImageIO.write((RenderedImage) imageCode.get("image"), "JPG", resp.getOutputStream());
	}


}
