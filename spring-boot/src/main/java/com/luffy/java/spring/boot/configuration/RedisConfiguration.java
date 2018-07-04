package com.luffy.java.spring.boot.configuration;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description jedis 配置中心
 */
@Configuration
@ConfigurationProperties
public class RedisConfiguration {

    @Value("${redis.pool.maxTotal}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.pool.minIdle}")
    private int minIdle;
    @Value("${redis.hosts.shard00.ip}")
    private String ip;
    @Value("${redis.hosts.shard00.port}")
    private int port;
    @Value("${redis.hosts.shard00.password}")
    private String password;
    @Value("${redis.hosts.shard00.name}")
    private String name;
    @Value("${redis.timeout}")
    private int timeout;


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    @Bean
    public JedisShardInfo jedisShardInfo() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(ip, port, name);
        if (StringUtils.isNotBlank(password)) {
            jedisShardInfo.setPassword(password);
        }
        jedisShardInfo.setConnectionTimeout(timeout);

        return jedisShardInfo;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        List<JedisShardInfo> jedisShardInfoList = Lists.newArrayList();
        jedisShardInfoList.add(jedisShardInfo());
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig(), jedisShardInfoList);
        return shardedJedisPool;
    }

}
