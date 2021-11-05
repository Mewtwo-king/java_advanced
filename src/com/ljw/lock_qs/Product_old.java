package com.ljw.lock_qs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product_old {
    public static void main(String[] args) {
        myNumber number = new myNumber();
        new Thread(()->{
            for (int i = 0;i<=1;i++){
                number.decrement();
            }
        },"bb").start();
        new Thread(()->{
            for (int i = 0;i<=1;i++){
                number.increment();
            }
        },"aa").start();

    }
}

class myNumber{
     int i = 0;
    private Lock lock =new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment()  {
        System.out.println("increment 进来");
        lock.lock();
        try {
            while(i==0){
                System.out.println("increment 等待");
               condition.await();
            }
             i++;
            System.out.println("i++: "+i);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement()  {
        lock.lock();
        System.out.println("decrement 进来");
        try {
            while(i!=0){
                System.out.println("decrement 等待");
                condition.await();
            }
            i--;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("i--: "+i);
            condition.signal();
            System.out.println("-------");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           // lock.unlock();
        }
    }
}
