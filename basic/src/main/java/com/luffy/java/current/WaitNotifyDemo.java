package com.luffy.java.current;

/**
 * Created by ps on 2018/11/27.
 *
 * wait：让持有该对象锁的线程等待，释放锁
 * notify：唤醒某持有该对象锁的线程
 * notifyAll：唤醒所有持有该对象锁的线程
 * 上述三个方法都是Object类型的方法
 */
public class WaitNotifyDemo {

  public static void main(String[] args) {
    Object lock = new Object();
    Thread t1 = new Thread(()->{
      synchronized (lock){
        for (int i=0;i<20;i++){
          System.out.println(i);
          if(i == 10){
            try {
              lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    });

    Thread t2 = new Thread(()->{
      synchronized (lock){
        for (int i=20;i<40;i++){
          System.out.println(i);
          if(i == 30){
            try {
              lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    });


    Thread t3 = new Thread(()->{
      while (true) {
        synchronized (lock){
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("线程2即将执行notifyAll欢迎所有持有对象锁且等待的线程");
          lock.notify();
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });


    t1.start();
    t2.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t3.start();
  }
}
