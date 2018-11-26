package com.luffy.java.proxy.statics;

/**
 * Created by ps on 2018/11/26.
 * @desc 静态代理
 */
public class StaticProxyFactory implements DemoInterface{

  private DemoService target;

  public StaticProxyFactory(DemoService target) {
    this.target = target;
  }

  @Override
  public int demo() {
    System.out.println("静态代替demo方法前执行...");
    int result = target.demo();
    System.out.println("静态代替demo方法后执行...");

    return result;
  }

  public static void main(String[] args) {
    new StaticProxyFactory(new DemoService()).demo();
  }
}
