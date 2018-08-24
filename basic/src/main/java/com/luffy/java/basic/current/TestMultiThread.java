package com.luffy.java.basic.current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Luffy
 * @date 2018/7/16
 * @description todo
 */
public class TestMultiThread implements Runnable{

    private static int i = 0;

    private static volatile Integer vi = 0;

    private static AtomicInteger ai = new AtomicInteger();

    private static Integer si = 0;

    private static int ri;

    private static AtomicInteger flag = new AtomicInteger();

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i=0;i<200;i++){
            i++;
            vi++;
            ai.incrementAndGet();
            synchronized (si){
                si++;
            }
            lock.lock();
            try {
                ri++;
            } finally {
                lock.unlock();
            }
        }
        flag.incrementAndGet();
    }

    public static void main(String[] args) {
        TestMultiThread thread1 = new TestMultiThread();
        TestMultiThread thread2 = new TestMultiThread();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService1.execute(thread1);
        executorService2.execute(thread2);
        while (true){
            if(flag.intValue() ==2){
                System.out.println("i>>>>>>>>"+i);
                System.out.println("vi>>>>>>>>"+vi);
                System.out.println("ai>>>>>>>>"+ai.intValue());
                System.out.println("si>>>>>>>>"+si);
                System.out.println("ri>>>>>>>>"+ri);
            }
        }
    }

}
