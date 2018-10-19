package com.luffy.rate.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Luffy
 * @date 2018/10/19
 * @description 水桶限流算法
 * 1、首先计算这次请求与上次请求来的时候，总共漏了多少水。
 * 2、看一下桶里面还剩多少水，有没有溢出。
 * 3、如果溢出了拒绝请求，如果没有添加当前一滴水。处理请求。
 */
public class LeakyBuckettLimiter {
    /**
     * 世纪计数值
     **/
    private volatile int water = 0;
    /**
     * 上次计数时间，精确到秒
     **/
    private volatile long lastTime = System.currentTimeMillis();
    /**
     * cas 自旋锁
     **/
    private Lock lock = new ReentrantLock();

    private volatile int rate;

    /**
     * 桶的容积，最多能容纳100个请求，多余的直接溢出
     **/
    private static final int capcity = 100;

    public LeakyBuckettLimiter(int rate) {
        this.rate = rate;
    }

    /**
     * 尝试获取请求许可
     **/
    public boolean tryAcquire() {
        // 加锁，线程安全问题
        lock.lock();
        try {
            long nowTime = System.currentTimeMillis();
            // 时间间隔内桶漏掉的水的容积
            int outWater = (int)(nowTime - lastTime)/1000 * rate;

            // 有漏水
            if(outWater > 0){
                lastTime = nowTime;
            }

            // 桶里是否还有水
            water = Math.max(0,water - outWater);

            if(water <= capcity){
                water ++ ;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public static void main(String[] args) {
        LeakyBuckettLimiter leakyBuckettLimiter = new LeakyBuckettLimiter(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
        for (int i = 0; i < 10; i++) {
            executor.submit(new LeakyBuckettLimiterThread(leakyBuckettLimiter));
        }
    }

}

class LeakyBuckettLimiterThread implements Runnable {

    private LeakyBuckettLimiter leakyBuckettLimiter;

    public LeakyBuckettLimiterThread(LeakyBuckettLimiter leakyBuckettLimiter) {
        this.leakyBuckettLimiter = leakyBuckettLimiter;
    }

    public void run() {
        while (true) {
            if (leakyBuckettLimiter.tryAcquire()) {
                System.out.println("获取锁成功");
            } else {
//                System.out.println("获取锁失败");
            }
        }
    }
}
