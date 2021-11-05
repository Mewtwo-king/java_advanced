package com.ljw.threadpool;

import java.util.concurrent.TimeUnit;

public class DeadLock_qs {
    public static void main(String[] args) {
        Integer lockA = 11;
        Integer lockB = 12;
        new Thread(new A(lockA,lockB),"AAAAA").start();
      //  new Thread(new A(lockB,lockA),"BBBBB").start();
    }
}
class A implements Runnable{
    private Integer lockA;
    private Integer lockB;
    public A(Integer lockA, Integer lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){

            System.out.println(Thread.currentThread().getName()+"自己持有----："+lockA+"尝试获得： "+lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+" 自己持有："+lockB+"尝试获得： "+lockA);
            }
        }

    }
}
