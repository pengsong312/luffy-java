package com.luffy.redisson.enums;

/**
 * Created by ps on 2018/11/21.
 */
public enum RedissonClientEnum {

  PROGRAM_CONFIGF_CLIENT(0,"程序配置加载Reddisson客户端"),
  JSON_CONFIGF_CLIENT(1,"JSON文件加载Reddisson客户端"),
  YAML_CONFIGF_CLIENT(2,"YAML文件加载Reddisson客户端"),
  XML_CONFIGF_CLIENT(3,"XML文件加载Reddisson客户端");

  private int type;
  private String desc;

  RedissonClientEnum(int type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
