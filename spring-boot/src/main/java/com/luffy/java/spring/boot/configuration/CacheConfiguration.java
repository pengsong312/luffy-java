package com.luffy.java.spring.boot.configuration;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description todo
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public EhCacheFactoryBean memberCache(){
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
        ehCacheFactoryBean.setCacheName("memberCache");
        return ehCacheFactoryBean;
    }

    @Bean
    public EhCacheFactoryBean moneyCache(){
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
        ehCacheFactoryBean.setCacheName("moneyCache");
        return ehCacheFactoryBean;
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("cache/ecache.xml"));
        return ehCacheManagerFactoryBean;
    }

}
