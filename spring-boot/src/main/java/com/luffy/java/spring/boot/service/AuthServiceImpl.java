package com.luffy.java.spring.boot.service;

import com.luffy.java.spring.boot.entity.AuthorSettings;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description todo
 */
@Service
public class AuthServiceImpl {

    @Resource(name = "memberCache")
    private Cache memberCache;

    @Resource(name = "moneyCache")
    private Cache moneyCache;

    /**
     * 使用注解缓存
     */
    @Cacheable(value = "memberCache", key = "#member")
    public AuthorSettings getAuthorSettings(String member) {
        System.out.println("开始查询.....");
        try {
            Thread.sleep(3 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查询结束......");
        return new AuthorSettings();
    }

    public String setMoneyCache(String money) {
        if (moneyCache.get(money) != null) {
            return moneyCache.get(money).getObjectValue().toString();
        } else {
            Element element = new Element(money,money);
            moneyCache.put(element);
            return "设置成功!";
        }

    }

    /**
     * 实例化缓存Cache，代码控制缓存
     */
    public String getResultFromEhcache(String member) {
        Element element = (Element) memberCache.get(member);
        Element element1 = (Element) moneyCache.get(member);
        String moneyResult = "moneyCache 缓存为空";
        if (element1 != null) {
            moneyResult = "命中 moneyCache result = " + element1.getObjectValue();
        }
        if (element != null) {
            return "命中 memberCache : key = " + element.getObjectKey() + " , value = " + element.getObjectValue() + " , " + moneyResult;
        } else {
            element = new Element(member, "Hello SpringBoot !");
            memberCache.put(element);
            try {
                Thread.sleep(3 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "未命中EhCache缓存 : key = " + member + " , value = " + element.getObjectValue() + " , " + moneyResult;
        }
    }

}