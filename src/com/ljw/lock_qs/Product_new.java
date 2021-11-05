package com.ljw.lock_qs;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Product_new {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data(new ArrayBlockingQueue(10));
        new Thread(()->{
            try {
                data.getData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            try {
                data.setData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
        TimeUnit.SECONDS.sleep(5);
        data.stop();
    }



}
class Data{
    private AtomicInteger integer = new AtomicInteger();
    private BlockingQueue queue;
    private volatile boolean flag = true;
    public Data(BlockingQueue queue) {
        this.queue = queue;
    }

    public void getData() throws InterruptedException {
        Object o = null;
        while (flag){
            o = queue.poll(2l, TimeUnit.SECONDS);
            if(o!=null&&!o.equals(" ")){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"消费成功一次"+o);
            }else {
                System.out.println("消费者获取不到资源---");
                flag = false;
                return;
            }
        }
    }

    public void setData() throws InterruptedException {
        String data = null;

        while (flag){
            data = integer.incrementAndGet()+"";
            boolean offer = queue.offer(data, 2l, TimeUnit.SECONDS);
            System.out.println("生产出的数据是： "+data);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"生产成功一次!");
                TimeUnit.SECONDS.sleep(1);
            }else {
                System.out.println("生产者出现问题!!!");
                flag = flag;
            }
        }

    }

    public void stop(){
        System.out.println("----------停止程序------");
        flag = false;
    }

}