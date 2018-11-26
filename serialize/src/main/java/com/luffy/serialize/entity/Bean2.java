package com.luffy.serialize.entity;

/**
 * Created by ps on 2018/11/21.
 */
public class Bean2 {

  private int id;
  private int beanId;
  private String beanName;
  private String beanDesc;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBeanId() {
    return beanId;
  }

  public void setBeanId(int beanId) {
    this.beanId = beanId;
  }

  public String getBeanName() {
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public String getBeanDesc() {
    return beanDesc;
  }

  public void setBeanDesc(String beanDesc) {
    this.beanDesc = beanDesc;
  }

  @Override
  public String toString() {
    return "Bean2{" +
        "id=" + id +
        ", beanId=" + beanId +
        ", beanName='" + beanName + '\'' +
        ", beanDesc='" + beanDesc + '\'' +
        '}';
  }
}
