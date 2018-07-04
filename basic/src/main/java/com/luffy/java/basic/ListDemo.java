package com.luffy.java.basic;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/7/3
 * @description List的常用操作
 */
public class ListDemo {

    public static final String strArray = "[-121231.121,0.2121,1110,1.0004218E7,13123.12,\'50001\',10004218]";

    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
        System.out.println("-------------------使用AliJson转---------------------");
        listDemo.useAliJsonObject();
        System.out.println("-------------------使用Google Gson转---------------------");
        listDemo.useGson();
        System.out.println("-------------------使用List.contains---------------------");
        listDemo.contains();
    }

    /**
     * 使用阿里fastJson将字符串转List 其中元素默认转成对应的元素类型，慎用
     */
    public void useAliJsonObject() {
        List intArray = JSONObject.parseObject(strArray, List.class);
        intArray.forEach((number) -> {
            System.out.println("useAliJsonObjectStr2Int元素[" + number + "]转变后的元素类型" + number.getClass().getName());
        });
    }

    /**
     * 使用Gson将字符串转list 其中元素为非字符转double，字符串转字符串 慎用
     */
    public void useGson() {
        List longArray = new Gson().fromJson(strArray, List.class);
        longArray.forEach((number) -> {
            System.out.println("useGsonStr2Dounble元素[" + number + "]转变后的元素类型" + number.getClass().getName());
        });
    }

    /**
     * List.contains() 必须包含的是list元素中对应数据类型相同
     */
    public void contains() {
        List aliParseList = JSONObject.parseObject(strArray, List.class);
        System.out.println("aliParseList.contains(10004218):" + aliParseList.contains(10004218));
        List gsonList = new Gson().fromJson(strArray, List.class);
        System.out.println("gsonList.contains(10004218):" + gsonList.contains(10004218));
    }

    /************************************ 源码分析 ************************************/
    /**
     * DEFAULT_CAPACITY = 10 初始化容量
     * Object[] EMPTY_ELEMENTDATA = {} 空数组对象
     * Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {} 默认容器空数据对象
     * transient Object[] elementData 数据存储对象
     * list size 大小
     * transient modCount 修改次数
     */
}
