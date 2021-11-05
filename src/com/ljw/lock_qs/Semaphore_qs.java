package com.ljw.lock_qs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphore_qs {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i =1; i<=6;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"占用到资源!  ");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"释放资源!  ");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
