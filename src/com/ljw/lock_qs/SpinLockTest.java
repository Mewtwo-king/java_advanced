package com.ljw.lock_qs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {
   private  AtomicReference<Thread> atomic = new AtomicReference();

   public void lock(){
       Thread thread = Thread.currentThread();
       System.out.println(Thread.currentThread().getName()+"com in!");
       while (!atomic.compareAndSet(null,thread)){

       }
   }

   public void unlock(){
       Thread thread = Thread.currentThread();
       atomic.compareAndSet(thread,null);
       System.out.println(Thread.currentThread().getName()+"释放锁!");
   }

    public static void main(String[] args) {
        SpinLockTest test = new SpinLockTest();
        new Thread(()->{
            test.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.unlock();
        },"t1").start();

        new Thread(()->{
            test.lock();
            test.unlock();
        },"t2").start();
    }
}
