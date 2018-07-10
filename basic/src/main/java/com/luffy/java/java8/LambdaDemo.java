package com.luffy.java.java8;

import java.util.Arrays;

/**
 * @author Luffy
 * @date 2018/7/9
 * @description Lambda 处理
 */
public class LambdaDemo {

    public static final String HELLO = "hello world !";

    public static void main(String[] args) {
        LambdaDemo lambdaDemo = new LambdaDemo();
        lambdaDemo.quoteField();
    }

    /**
     * Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的）
     */
    public void quoteField(){
        String separator = " ";
        Arrays.asList("a","b","c").forEach((String e)-> System.out.println(e+separator+HELLO));
    }

    public void returnField(){
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo(e2));

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );
    }
}
