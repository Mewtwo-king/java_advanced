package com.ljw.lock_qs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatch_qs {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock =new ReentrantLock();
        Condition condition = new ReentrantLock().newCondition();
        CountDownLatch latch = new CountDownLatch(4);
        for(int i = 1 ;i <= 4; i++){
            final int n = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"走出教师");
                latch.countDown();
            },City_Enum.getInstance(n).getName()).start();
        }
        latch.await();
        System.out.println("关门走人。。。");
    }
}
