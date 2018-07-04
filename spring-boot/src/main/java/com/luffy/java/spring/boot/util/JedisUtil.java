package com.luffy.java.spring.boot.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description jedis操作类
 */
@Component
public class JedisUtil {

    @Resource(name = "shardedJedisPool")
    private ShardedJedisPool shardedJedisPool;

    public boolean set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            if (!Objects.isNull(shardedJedis)) {
                shardedJedis.set(key, value);
                return true;
            }
        } catch (Exception e) {

        } finally {
            if (!Objects.isNull(shardedJedis)) {
                shardedJedis.close();
            }
        }
        return false;
    }

    public String get(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            if (!Objects.isNull(shardedJedis)) {
                return shardedJedis.get(key);
            }
        } catch (Exception e) {
        } finally {
            if (!Objects.isNull(shardedJedis)) {
                shardedJedis.close();
            }
        }
        return "error";
    }
}
