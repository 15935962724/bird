package com.bird.email;

import com.bird.util.DateUtil;
import com.bird.util.XmlUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件工具
 */
public class EmailUtil {

    public static void main(String[] args) throws Exception {

        EmailEntity email = new EmailEntity();
        email.setUserName("15935962724@163.com");
        email.setPassword("ZhuGeSiMa123");
        email.setHost("smtp.163.com");
        email.setPort(25);
        email.setFromAddress("15935962724@163.com");//发送方邮箱
        email.setToAddress("28112369@qq.com");//接收方邮箱
        email.setSubject("这是一封测试邮件!!!!");

        Map<String, String> data = new HashMap<String, String>();
        data.put("System", "Apsaras Brachy 3");
        data.put("Time", DateUtil.getDatetoString("yyyy-MM-dd HH:mm:ss",new Date()));
        data.put("HospitalName", "北京医科大学第三人民医院");
        data.put("Deadline", "10");
        data.put("CaseNumber", "0");
        data.put("SerialNumber", "dWRt5vhkWRDMaKUYz217vg==");
        data.put("url", "http://www.baidu.com");
        String content = XmlUtil.mapToXmlRequest(data);
        System.out.println(content);

        email.setContext(content);
        email.setContextType("text/html;charset=utf-8");
        boolean flag = EmailSend.EmailSendTest(email);
        System.err.println("邮件发送结果=="+flag);

    }

}