package com.luffy.serialize.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.luffy.serialize.entity.Bean1;
import com.luffy.serialize.entity.Bean2;

/**
 * Created by ps on 2018/11/21.
 * @desc 使用kryo进行序列化与反序列化时注意：反序列化对象中的属性必须要大于或等于序列化对象中的属性，否则报：StringIndexOutOfBoundsException
 */
public class KryoSerializerUtil {
  private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
    protected Kryo initialValue() {
      Kryo kryo = new Kryo();
      kryo.register(Bean1.class);
      kryo.register(Bean2.class);
      return kryo;
    }
  };

  private KryoSerializerUtil() {
  }

  public static byte[] serialize(Object object) {
    Kryo kryo = kryos.get();
    Output output = new Output(10, 65535);
    kryo.writeObject(output, object);
    output.close();
    return output.getBuffer();

  }


  public static <T> T deSerialize(byte[] data, Class<T> type) {
    Kryo kryo = kryos.get();
    Input input = new Input(data);
    T t = kryo.readObject(input, type);
    input.close();
    return t;
  }
}
