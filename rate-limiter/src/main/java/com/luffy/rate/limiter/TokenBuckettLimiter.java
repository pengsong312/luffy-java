package com.luffy.rate.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Luffy
 * @date 2018/10/19
 * @description 令牌桶限流算法
 * 思路：匀速的产生令牌，往桶里面丢，每次请求来，看是否有多余的令牌。如果有获取令牌执行正常业务，偌没有限速。
 * 1、请求来的时候先计算目前放入桶中的令牌数，这里计算，就可以不用启动一个线程匀速放置令牌了，这个叫惰性计算。
 * 2、然后计算桶拥有的令牌数。然后获取令牌。做拒绝还是处理动作。
 */
public class TokenBuckettLimiter {
    /**
     * 世纪计数值
     **/
    private volatile int token = 0;
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

    public TokenBuckettLimiter(int rate) {
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
            // 匀速往令牌桶放置了多少令牌
            int inToken = (int)(nowTime - lastTime)/1000 * rate;

            // 有漏水
            if(inToken > 0){
                lastTime = nowTime;
            }

            // 桶里这段时间的所有令牌数
            token += inToken;
            // 去掉溢出的令牌
            token = Math.min(token,capcity);

            if(token - 1 > 0){
                token-- ;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public static void main(String[] args) {
        TokenBuckettLimiter tokenBuckettLimiter = new TokenBuckettLimiter(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
        for (int i = 0; i < 10; i++) {
            executor.submit(new TokenBuckettLimiterThread(tokenBuckettLimiter));
        }
    }

}

class TokenBuckettLimiterThread implements Runnable {

    private TokenBuckettLimiter tokenBuckettLimiter;

    public TokenBuckettLimiterThread(TokenBuckettLimiter tokenBuckettLimiter) {
        this.tokenBuckettLimiter = tokenBuckettLimiter;
    }
    
    public void run() {
        while (true) {
            if (tokenBuckettLimiter.tryAcquire()) {
                System.out.println("获取锁成功");
            } else {
//                System.out.println("获取锁失败");
            }
        }
    }
}
