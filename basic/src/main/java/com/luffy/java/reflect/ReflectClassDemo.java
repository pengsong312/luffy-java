package com.luffy.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Luffy
 * @date 2018/8/24
 * @description 反射class案例
 */
public class ReflectClassDemo extends Reflect{

    /** boolean 数组类型 **/
    public boolean[] booleans = {false,false};

    public String name = "luffy-java";

    public Integer integer = 64;

    public static final String CLASS_NAME = "com.luffy.java.reflect.ReflectClassDemo";


    public static void main(String[] args) {
        try {
            Class<?> tClass = Class.forName(CLASS_NAME);
            Field[] fields =  tClass.getFields();
            for (Field field : fields) {
                System.out.println("Field:"+field.getName());
                System.out.println("Type: CanonicalName = " + field.getType().getCanonicalName() + ",SimpleName = " + field.getType().getSimpleName() + ",name = " + field.getType().getName());
                System.out.println("GenericType:"+field.getGenericType().toString());
                System.out.println("Modifier:"+ Modifier.toString(field.getModifiers()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

abstract class Reflect{

}
