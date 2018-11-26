package com.luffy.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luffy.serialize.entity.Bean1;
import com.luffy.serialize.entity.Bean2;
import com.luffy.serialize.util.BeanInitUtil;

/**
 * Created by ps on 2018/11/21.
 */
public class JsonSerializeDemo {

  public static void main(String[] args) {
    Bean1 bean1 = BeanInitUtil.initBean1();
    String bean1Str = JSON.toJSONString(bean1);
    System.out.println("bean1 反序列化bean2 :"+ JSONObject.parseObject(bean1Str,Bean2.class));

    Bean2 bean2 = BeanInitUtil.initBean2();
    String bean2Str = JSON.toJSONString(bean2);
    System.out.println("bean2 反序列化bean1 :"+ JSONObject.parseObject(bean2Str,Bean1.class));

  }
}
