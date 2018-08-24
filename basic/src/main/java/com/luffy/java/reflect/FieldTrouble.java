package com.luffy.java.reflect;

import java.lang.reflect.Field;

/**
 * @author Luffy
 * @date 2018/8/24
 * @description todo
 */
public class FieldTrouble extends Reflect {

    public Integer value;
    public final boolean wannaPlayGame = true;

    public static void main(String[] args) {
        FieldTrouble fieldTrouble = new FieldTrouble();
        Class<? extends FieldTrouble> cls = fieldTrouble.getClass();
        try {
            Field value = cls.getField("value");
            /** value 类型Integer 出现类型转换错误：
             * java.lang.IllegalArgumentException: Can not set java.lang.Integer field com.luffy.java.reflect.FieldTrouble.value to (int)23**/
//            value.setInt(fieldTrouble, 23);
            value.set(fieldTrouble, new Integer(23));
            System.out.format("Field：%s    %s\n", value.getName(),fieldTrouble.value);


            Field wannaPlayGame = cls.getDeclaredField("wannaPlayGame");
            /** final 修饰的时不设置setAccessible(true)报错:java.lang.IllegalAccessException: Can not set final**/
            /** setAccessible(true) 方法前也需要注意，这可能会导致意想不到的后果，
             * 比如：在运行时虽然你通过反射修改了变量 a 的值，但其他部分可能还在使用原来的值。 **/
            wannaPlayGame.setAccessible(true);
            wannaPlayGame.setBoolean(fieldTrouble, false);
            /** 打印结果 true **/
            System.out.format("Field：%s    %s\n", wannaPlayGame.getName(),fieldTrouble.wannaPlayGame);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

