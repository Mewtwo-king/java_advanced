package com.ljw.lock_qs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CyclicBarrier_qs {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(6,()->{
            System.out.println("等待到其它线程完工!");
        });
        for (int i = 1; i<=6;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"开始工作! ");
                    TimeUnit.SECONDS.sleep(3);
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
