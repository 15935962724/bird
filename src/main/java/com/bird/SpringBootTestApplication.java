package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ImportResource(locations= {"classpath:beans.xml"})//导入指定的配置文件
@MapperScan(value = "com.bird.dao")
@SpringBootApplication

//@ComponentScan(basePackages={"com.bird"})
public class SpringBootTestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootTestApplication.class, args);
        System.err.println("bird启动完毕");
    }

}
