package com.bird.controller.admin;

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

@Controller("adminIndex")
@RequestMapping("/admin/index")
public class IndexController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;

	/**
	 * 后台管理主页
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		return "admin/index";
	}



}
