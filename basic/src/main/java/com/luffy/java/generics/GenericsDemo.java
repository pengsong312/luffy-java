package com.luffy.java.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luffy
 * @date 2018/8/27
 * @description 范型范例
 */
public class GenericsDemo {

    public static void main(String[] args) {
//        demo1();

        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<String>("key_vlaue");

        // 不能对确切的泛型类型使用instanceof操作，下面的操作是非法的，编译时直接报错
//        if(genericString instanceof Generic<String>){
//
//        }
        // instanceof Generic
        if (genericString instanceof Generic<?>) {
            System.out.println("true");
        }
        System.out.println("泛型测试 key is " + genericInteger.getKey());
        System.out.println("泛型测试 key is " + genericString.getKey());

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        //编译器会为我们报错：Generic<java.lang.Integer>
        // cannot be applied to Generic<java.lang.Number>
//        showKeyValue1(gInteger);
        showKeyValue1(gNumber);


        /** 泛型擦除 **/
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();

    }

    /**
     * 范型错误：java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
     **/
    public static void demo1() {
        List list = new ArrayList();
        list.add("aaaa");
        list.add(100);
        for (int i = 0; i < list.size(); i++) {
            String item = (String) list.get(i);
            System.out.println("item = " + item);
        }
    }

    public static void showKeyValue1(Generic<Number> obj) {
        System.out.println("泛型测试 key value is " + obj.getKey());
    }
}

class Generic<T> {
    /**
     * key这个成员变量的类型为T,T的类型由外部指定，且不能是简单类型
     **/
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}

/***
 * 静态方法与范型
 * @param <T>
 */
class StaticGenerator<T> {
    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法） 即使静态方法要使用泛型类中已经声明过的泛型也不可以。 如：public static void
     * show(T t){..},此时编译器会提示错误信息： "StaticGenerator cannot be refrenced from static context"
     */
    public static <T> void show(T t) {

    }
//    public static void show(T t){
//
//    }
}

class HasF {
    public void f() {
        System.out.println("HasF.f()");
    }
}

class Manipulator<T> {
    private T obj;

    public Manipulator(T x) {
        obj = x;
    }

    /** Error: cannot find symbol: method f()**/
    public void manipulate() {
//        obj.f();
    }
}