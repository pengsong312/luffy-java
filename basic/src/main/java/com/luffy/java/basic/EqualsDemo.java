package com.luffy.java.basic;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/7/2
 * @description 对 java中equals 和 == 进行研究和范例
 */
public class EqualsDemo {

    public static void main(String[] args) {
        EqualsDemo equalsDemo = new EqualsDemo();
        equalsDemo.equals2Demo();
        equalsDemo.equalsDemo();

        // 校验Long数据
        String strArray = "[10004218,1110,1.0004218E7,13123.12]";
        List longArray = new Gson().fromJson(strArray, List.class);
        longArray.forEach((number) -> {
            System.out.println("longArray:" + number);
            System.out.println("longArray:" + number.getClass().getName());

        });
        System.out.println(longArray.contains(Double.valueOf(10004218)));

        List intArray = JSONObject.parseObject(strArray, List.class);
        intArray.forEach((number) -> {
            System.out.println("intArray:" + number);
            System.out.println("intArray 转变后的元素类型" + number.getClass().getName());
        });
        System.out.println(intArray.contains(10004218));
        List<Integer> list = Lists.newArrayList(10004218, 1110);

    }

    /**
     * 判断 equals 的操作:
     * equals用来比较的是两个对象的内容是否相等，由于所有的类都是继承自java.lang.Object类的，所以适用于所有对象，
     * 如果没有对该方法进行覆盖的话，调用的仍然是Object类中的方法，而Object中的equals方法返回的却是==的判断。
     **/
    public void equalsDemo() {
        // 基本类型
        int upHundred = 128;
        // false 类型不匹配 "128" 字符串 upHundred是数值
        System.out.println("equalsDemo:" + "128".equals(upHundred));
        // true 比较是128中堆内存中的值
        System.out.println("equalsDemo:" + "128".equals(String.valueOf(upHundred)));

        String str1 = "abc";
        String str2 = new String("abc");

        System.out.println("equalsDemo:" + str1.equals("abc"));
        System.out.println("equalsDemo:" + str1.equals(str2));
    }

    /**
     * 判断 == 的操作:
     * 比较的是变量(栈)内存中存放的对象的(堆)内存地址，用来判断两个对象的地址是否相同，
     * 即是否是指相同一个对象。比较的是真正意义上的指针操作。
     **/
    public void equals2Demo() {
        /********************
         * (1)基本类型比较：
         * byte char short int float double long boolean
         * 基本数据类型，也称原始数据类型 byte,short,char,int,long,float,double,boolean
         * 他们之间的比较，应用双等号（==）,比较的是他们的值。
         * *********************/
        // byte
        byte byte1 = 'a';
        byte byte2 = 'a';
        // true
        System.out.println("equalsDemo byte1 == byte2 :" + (byte1 == byte2));

        short short1 = 1;
        short short2 = 1;
        // true
        System.out.println("equalsDemo short1 == short2 :" + (short1 == short2));

        // int 类型判断
        int upHundred = 128;
        int upthousand = 1280000000;
        int downHundred = 12;

        // true
        System.out.println(upHundred == 128);
        // true
        System.out.println(upthousand == 1280000000);
        // true
        System.out.println(downHundred == 12);

        char char1 = 'a';
        char char2 = 'a';
        // true
        System.out.println(char1 == char2);

        /********************
         * (2)复合数据类型(类):
         * 1. 当他们用（==）进行比较的时候，比较的是他们在内存中的存放地址
         * 2. JVM会在常量池中
         * *********************/

        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = str1;
        // 字符串缓冲池
        String str4 = "abc";
        String str5 = "abc";

        // false
        System.out.println(str1 == str2);
        // true
        System.out.println(str1 == str3);
        // true
        System.out.println(str4 == str5);

        Object obj1 = new Object();
        Object obj2 = new Object();
        // false
        System.out.println(obj2 == obj1);

    }
}
