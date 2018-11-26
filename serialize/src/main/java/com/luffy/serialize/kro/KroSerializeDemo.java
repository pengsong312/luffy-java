package com.luffy.serialize.kro;

import com.alibaba.fastjson.JSONObject;
import com.luffy.serialize.entity.Bean1;
import com.luffy.serialize.entity.Bean2;
import com.luffy.serialize.util.BeanInitUtil;
import com.luffy.serialize.util.KryoSerializerUtil;

/**
 * Created by ps on 2018/11/21.
 * @desc 使用kryo进行序列化与反序列化时注意：反序列化对象中的属性必须要大于或等于序列化对象中的属性，否则报：StringIndexOutOfBoundsException
 */
public class KroSerializeDemo {

  public static void main(String[] args) {
    Bean1 bean1 = BeanInitUtil.initBean1();
    byte[] bean1Byte = KryoSerializerUtil.serialize(bean1);
    System.out.println("bean1 反序列化bean1 :"+ KryoSerializerUtil.deSerialize(bean1Byte,Bean1.class));
    System.out.println("bean1 反序列化bean2 :"+ KryoSerializerUtil.deSerialize(bean1Byte,Bean2.class));

    Bean2 bean2 = BeanInitUtil.initBean2();
    byte[] bean2Byte = KryoSerializerUtil.serialize(bean2);
    System.out.println("bean2 反序列化bean1 :"+ JSONObject.parseObject(bean2Byte,Bean1.class));
  }
}
