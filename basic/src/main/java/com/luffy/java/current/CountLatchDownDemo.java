package com.luffy.java.current;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ps on 2018/11/27.
 * @定义 主线程在等待所有其它的子线程完成后再往下执行
 * @desc CountLatchDown 闭锁使用方法，调用CountLatchDown.await()时表示当CountLatchDown
 * 中的值=0时，放行所有的线程。
 * @场景 一般用户阻塞其他自线程完成所有操作后在启动开启另外的操作
 *
 * @题目：三人参加赛跑，只有三个都跑到重点后才全部比赛结果
 */
public class CountLatchDownDemo {

  /** 拦截子线程个数 **/
  private static final CountDownLatch LATCH = new CountDownLatch(3);

  public static void main(String[] args) {
    CountLatchDownDemo demo = new CountLatchDownDemo();
    // 参赛者1 达到重点
    new Thread(()->{
      demo.runner1();
      // 进入等待状态
      LATCH.countDown();
    }).start();

    // 参赛者2 达到重点
    new Thread(()->{
      demo.runner2();
      LATCH.countDown();
    }).start();

    // 参赛者3 达到重点
    new Thread(()->{
      demo.runner3();
      LATCH.countDown();
    }).start();

    try {
      // 主线程等待LATCH等于0
      LATCH.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    demo.declareResult();
  }

  public void runner1(){
    System.out.println("runner1 400米赛跑，耗时：64s");
  }

  public void runner2(){
    System.out.println("runner2 400米赛跑，耗时：60s");
  }

  public void runner3(){
    System.out.println("runner3 400米赛跑，耗时：67s");
  }

  public void declareResult(){
    System.out.println("宣布比赛结果:获取第一名的是runner2");
  }


}
