package com.bird.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {


	public static void main(String[] args) {



		String realName = "北京市,大兴区";
		System.out.println(realName.lastIndexOf(","));
		System.out.println(realName.substring(0,realName.lastIndexOf(",")));
		String realName2 = "河北省,石家庄,人人区";
		System.out.println(realName2.lastIndexOf(","));
		System.out.println(realName2.substring(0,realName2.lastIndexOf(",")));
	}

}
