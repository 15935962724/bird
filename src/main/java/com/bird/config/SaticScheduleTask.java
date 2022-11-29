package com.bird.config;
import com.alibaba.fastjson.JSONObject;
import com.bird.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.Date;


@Configuration
@EnableScheduling
public class SaticScheduleTask {


    @Value("${wxAppid}")
    private String wxAppId;

    @Value("${wxSecret}")
    private String wxSecret;


    //3.添加定时任务
    @Scheduled(cron = "0 0 0/2 * * ? ")
    //或直接指定时间间隔，例如：5秒
    private void configureTasks() {

        JSONObject wx = new JSONObject();
        wx.put("appId",wxAppId);
        wx.put("secret",wxSecret);
        String access_token = WeiXinUtils.getAccess_token(wx);
        System.out.println("定时任务获取accessToken:"+access_token);
        Setting setting = SettingUtils.get();
        setting.setAccessToken(access_token);
        SettingUtils.set(setting);

        System.err.println("执行静态定时任务时间: " + DateUtil.getDatetoString("yyyy-HH-dd hh:mm:ss ",new Date()));
    }

    public static void main(String[] args) {
        JSONObject wx = new JSONObject();
        wx.put("appId","wx216689b717dbc269");
        wx.put("secret","39c409942dd0c7217a0ce1fba59e90b3");
        String access_token = WeiXinUtils.getAccess_token(wx);
        System.out.println("获取accessToken:"+access_token);

    }


}