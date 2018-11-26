package com.luffy.serialize.util;

import com.luffy.serialize.entity.Bean1;
import com.luffy.serialize.entity.Bean2;

/**
 * Created by ps on 2018/11/21.
 */
public class BeanInitUtil {

  public static final Bean1 initBean1(){
    Bean1 bean1 = new Bean1();
    bean1.setBeanId(100);
    bean1.setBeanName("bean1");
    bean1.setBeanDesc("bean1 实例化");
    return bean1;
  }

  public static final Bean2 initBean2(){
    Bean2 bean2 = new Bean2();
    bean2.setBeanId(1000000);
    bean2.setBeanId(200);
    bean2.setBeanName("bean2");
    bean2.setBeanDesc("bean2 实例化");
    return bean2;
  }
}
