package com.luffy.java.spring.boot.controller;

import com.luffy.java.spring.boot.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luffy
 * @date 2018/3/20
 * @description demo controller
 */
@RestController
@EnableAutoConfiguration
public class DemoController {

    // 使用注解获取properties文件中的属性
    @Value("${author.name}")
    private String name;

    @Autowired
    private AuthServiceImpl authService;

    @RequestMapping("/")
    public String demo() {
        return "Hello " + name + " !";
    }

    @RequestMapping("/testCache/{name}")
    public String test(@PathVariable("name") String name) {
        Long startTime = System.currentTimeMillis();
        authService.getAuthorSettings(name);
        return "this is a test request , 耗时: " + (System.currentTimeMillis() - startTime);
    }

    @RequestMapping("/testCache2/{member}")
    public String test2(@PathVariable("member") String member) {
        Long startTime = System.currentTimeMillis();
        return authService.getResultFromEhcache(member) + ", 耗时: " + (System.currentTimeMillis() - startTime);
    }

    @RequestMapping("/money/{num}")
    public String setMoney(@PathVariable("num") String num) {
        return authService.setMoneyCache(num);
    }
}
