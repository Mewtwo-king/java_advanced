package com.ljw.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueue_qs {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(3);
        queue.put(1);
        queue.put(1);
        queue.put(1);
        BlockingQueue queue1 = new SynchronousQueue();
        queue1.put(1);
        queue1.put(1);
        queue1.put(1);
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"生产"+ 1 +"次");
                    queue1.put(1);
                    System.out.println(Thread.currentThread().getName()+"生产"+ 2 +"次");
                    queue1.put(2);
                    System.out.println(Thread.currentThread().getName()+"生产"+ 3 +"次");
                    queue1.put(3);
                    System.out.println(Thread.currentThread().getName()+"生产"+ 4 +"次");
                    queue1.put(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"aaa").start();
            new Thread(()-> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "获取" + 1 + "次");
                    queue1.take();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "获取" + 2 + "次");
                    queue1.take();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "获取" + 3 + "次");
                    queue1.take();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "获取" + 4 + "次");
                    queue1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"bbb").start();
    }
}
