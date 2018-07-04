package com.luffy.java.junit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Luffy
 * @date 2018/7/3
 * @description 单元测试demo
 */
public class JunitDemo {
    private static Calculator calculator = new Calculator();

    @Before
    public void setUp(){
        calculator.clear();
    }

    @Test
    public void add(){
        calculator.add(3);
        calculator.add(4);
    }

    @Test
    public void substract(){
        calculator.add(8);
        calculator.substract(2);
        assertEquals(6, calculator.getResult());
    }

    @Ignore("Multiply() Not yet implemented")
    @Test
    public void testMultiply() {
        fail("Not yet implemented .......");
    }

    @Test
    public void testDivide() {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult());

    }

}


class Calculator {
    /**
     * 静态变量，用于存储运行结果
     **/
    private static int result;

    public void add(int n) {
        result = result + n;
    }

    public void substract(int n) {
        //Bug: 正确的应该是 result =result-n
        result = result - n;
    }


    public void multiply(int n) {
        // 此方法尚未写好
    }

    public void divide(int n) {
        result = result / n;
    }

    public void square(int n) {
        result = n * n;
    }

    public void squareRoot(int n) {
        //Bug : 死循环
        for (; ; ){

        }
    }

    public void clear() {
        // 将结果清零
        result = 0;
    }

    public int getResult() {
        return result;
    }
}
