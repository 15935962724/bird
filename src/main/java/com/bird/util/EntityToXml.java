/*
 * Copyright 2005-2013 shenzhou.net. All rights reserved.
 * Support: http://www.shenzhou.net
 * License: http://www.shenzhou.net/license
 */
package com.bird.util;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;


import java.util.Date;
import java.util.UUID;

/**
 * Utils - Web
 * 
 * @author HaoKangHu Team
 * @version 1.0
 */
public class EntityToXml {



	/**
	 * Map转成实体对象
	 *
	 * @param map   map实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static <T> T map2Object(HashMap<String, Object> map, Class<T> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}

	/**
	 * 将对象直接转换成String类型的 XML输出
	 */
	public static String convertToXml(Object clazz, String encoding) {
		// 创建输出流
		StringWriter sw = new StringWriter();
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(clazz.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			// 将对象转换成输出流形式的xml
			marshaller.marshal(clazz, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}



}