package com.luffy.java.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ps on 2018/11/26.
 * @desc 动态代理 目标对象必须实现一个接口
 */
public class DynamicProxyFactory {

  private Object target;

  public DynamicProxyFactory(Object target) {
    this.target = target;
  }

  public Object getInstance(){
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(target,args);
          }
        });
  }

  public static void main(String[] args) {
    ((DynamicProxyInterface)new DynamicProxyFactory(new DynamicClass()).getInstance()).dynamicMethod();
  }
}

interface DynamicProxyInterface{

  void dynamicMethod();
}

class DynamicClass implements DynamicProxyInterface{

  @Override
  public void dynamicMethod() {
    System.out.println("这是目标对象的dynamicMethod...");
  }
}
