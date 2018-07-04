package com.luffy.java.spring.boot;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Luffy
 * @date 2018/3/20
 * @description todo
 */
@SpringBootApplication
// 设置调度任务开启
@EnableScheduling
// 开启事务管理
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("项目启动时间:"+sdf.format(new Date()));
        System.setProperty("project.name","spring-boot");
        System.out.println(JSONObject.toJSONString(System.getProperties()));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("程序钩子退出 。。。");
            }
        }));
    }
}
