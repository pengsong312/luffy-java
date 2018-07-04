package com.luffy.java.spring.boot.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Luffy
 * @date 2018/3/20
 * @description 调度任务
 */
@Component
public class DemoScheduledTask {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 2000L)
    public void fixedDelay() {
        System.out.println("fixedDelay 当前系统时间:" + sdf.format(new Date()));
    }

    /**
     * 固定频率触发
     **/
    @Scheduled(initialDelay = 2000L, fixedDelay = 1000L)
    public void initDeplayAndFixedDelay() {
        System.out.println("initDeplayAndFixedDelay 当前系统时间:" + sdf.format(new Date()));
    }

    /**
     * 固定频率触发
     **/
    @Scheduled(fixedRate = 3000L)
    public void scheduledFixRate() {
        System.out.println("scheduledFixRate 固定频率调度任务:" + sdf.format(new Date()));
    }

    /**
     * 延时 固定频率触发
     **/
    @Scheduled(initialDelay = 2000L, fixedRate = 5000L)
    public void scheduledInitDelayAndFixRate() {
        System.out.println("scheduledInitDelayAndFixRate 当前系统时间:" + sdf.format(new Date()));
    }

    /**
     * 固定时间触发
     **/
    @Scheduled(cron = "1 4 15 * * *")
    public void scheduledCron() {
        System.out.println("scheduledCron 当前系统时间:" + sdf.format(new Date()));
    }
}
