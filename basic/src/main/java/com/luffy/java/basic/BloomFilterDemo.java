package com.luffy.java.basic;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luffy
 * @date 2018/12/11
 * @description
 * 题目：
 * 在亿数量级的元素中判断是否存在某个元素
 *
 * 思路：
 * 1 使用 HashMap load所有元素到内存，然后判断是否存在
 * 2 使用 bloomfilter 判断是否存在,维护一个L长度的数组，且槽全部是设置为1，
 * 对存储的object对应进行N次hash取模，将index所在槽设置成1，判断某个元素是否存在，采用同样的算法，
 * 并且取得的index槽全部为1的时候判断存在，存在误报，原因是不同的object hash取模后可能发生hash碰撞，精确性取决与数组的L长度和Hash N次数
 * 3 使用 guava 工具实现
 */
public class BloomFilterDemo {

    public static void main(String[] args) {
        new BloomFilterDemo().mapContains(99999);
        new BloomFilterDemo().guavaContains(99999);

    }

     public boolean mapContains(int target){
         Map<Integer,Object> map = new HashMap<Integer, Object>(10000000);
         long start = System.currentTimeMillis();
         for(int i=0;i<10000000;i++){
             map.put(i,null);
         }
         boolean result = map.containsKey(target);
         System.out.println("mapContains costTime:"+(System.currentTimeMillis() - start)+",result:"+result);
         return result;
     }

     public boolean guavaContains(int target){
         BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(),10000000,0.01);
         long start = System.currentTimeMillis();
         for(int i=0;i<10000000;i++){
             bloomFilter.put(i);
         }

         boolean result = bloomFilter.mightContain(target);
         System.out.println("guavaContains costTime:"+(System.currentTimeMillis() - start)+",result:"+result);
         return result;
     }


}
