package com.luffy.rate.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Luffy
 * @date 2018/10/19
 * @description 程序限流计数器
 */
public class CounterLimiter {
    /**
     * 世纪计数值
     **/
    private volatile int counter = 0;
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
     * 单位时间1秒限流计数
     **/
    private static final int time = 1000;

    public CounterLimiter(int rate) {
        this.rate = rate;
    }

    /**
     * 尝试获取请求许可
     **/
    public boolean tryAcquire() {
        // 加锁，线程安全问题
        lock.lock();
        try {
            /**
             * 锁的粒度过大，如果请求在前1秒的10毫秒内进入10个请求且后1秒前10毫秒进入10个请求，导致时间窗口20毫秒会出现20个请求
             */
            if ((System.currentTimeMillis() - lastTime) > time) {
                counter = 0;
                lastTime = System.currentTimeMillis();
            }
            // 请求计数
            counter++;
            // 计数未达到阈值
            if (counter <= rate) {
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public static void main(String[] args) {
        CounterLimiter counterLimiter = new CounterLimiter(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
        for (int i = 0; i < 10; i++) {
            executor.submit(new CounterLimiterThread(counterLimiter));
        }
    }

}

class CounterLimiterThread implements Runnable {

    private CounterLimiter counterLimiter;

    public CounterLimiterThread(CounterLimiter counterLimiter) {
        this.counterLimiter = counterLimiter;
    }

    public void run() {
        while (true) {
            if (counterLimiter.tryAcquire()) {
                System.out.println("获取锁成功");
            } else {
//                System.out.println("获取锁失败");
            }
        }
    }
}
