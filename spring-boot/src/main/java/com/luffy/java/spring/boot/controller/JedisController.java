package com.luffy.java.spring.boot.controller;

import com.luffy.java.spring.boot.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description jedis 操作
 */
@RestController
public class JedisController {

    @Autowired
    private JedisUtil jedisUtil;

    @RequestMapping("set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        boolean isSuccess = jedisUtil.set(key, value);
        if(isSuccess){
            return "success ...";
        }else{
            return "failed ...";
        }
    }

    @RequestMapping("get/{key}")
    public String get(@PathVariable("key") String key) {
        return jedisUtil.get(key);
    }
}
