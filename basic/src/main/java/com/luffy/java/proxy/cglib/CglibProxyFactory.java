package com.luffy.java.proxy.cglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Created by ps on 2018/11/26.
 * @desc cglib代理
 */
public class CglibProxyFactory implements MethodInterceptor{

  private Object target;

  public CglibProxyFactory(Object target) {
    this.target = target;
  }

  public Object getInstance(){
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
      return method.invoke(target,objects);
  }

  public static void main(String[] args) {
    ((CglibProxyClass)new CglibProxyFactory(new CglibProxyClass()).getInstance()).cglibMethod();
  }
}

class CglibProxyClass{

  public void cglibMethod(){
    System.out.println("这是CglibProxyClass的cglibMethod方法...");
  }
}
