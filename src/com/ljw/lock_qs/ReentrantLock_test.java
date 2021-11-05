package com.ljw.lock_qs;

import java.util.concurrent.TimeUnit;

public class ReentrantLock_test {

    public static void main(String[] args) {
        Phone p = new Phone();
        new Thread(()->{
            try {
                p.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread one").start();

        new Thread(()->{
            try {
                p.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread two").start();
    }
}

class Phone{
    public synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+"\t send message ");
        sendEmail();
    }
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+"\t send email ");
    }
}
