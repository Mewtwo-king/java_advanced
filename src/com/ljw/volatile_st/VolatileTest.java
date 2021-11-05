package com.ljw.volatile_st;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        MyData myData =new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"得到数据"+myData.i);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addI();
            System.out.println(Thread.currentThread().getName()+"update"+myData.i);
        },"t1").start();

        while (myData.i == 10){

           // System.out.println("---"+myData.i);

        }
    }
}
class MyData{
       int i = 10;
    public void addI(){
        this.i = 14;
    }

}