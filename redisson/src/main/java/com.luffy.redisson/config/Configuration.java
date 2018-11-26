package com.luffy.redisson.config;

import com.luffy.redisson.enums.RedissonClientEnum;
import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * Created by ps on 2018/11/21.
 */
public final class Configuration {

  public static void main(String[] args) {
    RedissonClient client = Configuration.getRedissonClient(RedissonClientEnum.PROGRAM_CONFIGF_CLIENT);
    RAtomicLong atomicLong = client.getAtomicLong("test_client");
    if(atomicLong.isExists()){
      atomicLong.delete();
    }
    RFuture<Void> result = atomicLong.setAsync(10000L);
    if(result.isSuccess()){
      System.out.println("测试联通成功，且设置key：test_client,value:"+atomicLong.get());
    }
  }

  public static final RedissonClient getRedissonClient(RedissonClientEnum clientEnum) {
    return initRedissonClient(clientEnum);
  }

  public static final RedissonClient initRedissonClient(RedissonClientEnum clientEnum) {

    Config config = null;
    switch (clientEnum) {
      case PROGRAM_CONFIGF_CLIENT:
        config = programConfig();
        break;
      case JSON_CONFIGF_CLIENT:
        try {
          config = jsonFileConfig();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      default:
        break;
    }
    return config == null ? null : Redisson.create(config);
  }

  /**
   * 程序配置redisson
   */
  public static final Config programConfig() {
    Config config = new Config();
    config.setTransportMode(TransportMode.NIO);
    config.useSingleServer().setAddress("redis://r-2ze00de0df6cba34.redis.rds.aliyuncs.com:6379").setPassword("4ftYkTqfPU8plQ");
    return config;
  }

  public static final Config jsonFileConfig() throws IOException {
    Config config = Config.fromJSON("file.json");
    return config;
  }


}
