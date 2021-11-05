package com.ljw.volatile_st;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNotAtomic {

    public static void main(String[] args) {
        Map map = new HashMap();
        Map map1 = new Hashtable();
        MyDate2 myData2 =new MyDate2();
        for (int i = 1;i<=20;i++){
            new Thread(()->{
                for (int j = 1;j<=1000;j++){
                    myData2.iIncrement();
                    myData2.atomicIntegerInc();
                }

            },"线程"+i).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("运算结果: "+myData2.i);
        System.out.println("运算结果2: "+myData2.atomicInteger.get());
    }
}

class MyDate2{
    int i = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public synchronized void  iIncrement(){
        i++;
    }
    void atomicIntegerInc(){
        atomicInteger.getAndIncrement();
    }
}