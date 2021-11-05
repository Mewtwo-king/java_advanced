package com.ljw.lock_qs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_th {
    public static void main(String[] args) {
        t_number number = new t_number();
        new Thread(()->{
            for (int i = 0;i<5;i++){
                number.conditionTwoAdd();
            }
        }).start();

        new Thread(()->{
            for (int i = 0;i<5;i++){
                number.conditionOneAdd();
            }
        }).start();

        new Thread(()->{
            for (int i = 0;i<5;i++){
                number.conditionThreeAdd();
            }
        }).start();
    }
}

class t_number{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void conditionOneAdd(){
        lock.lock();
        try {
            while (number!=1){
                System.out.println("condition1 被挂起-------");
                condition1.await();
            }
            for (int i =0;i<5;i++){
                System.out.println("condition1执行=====5次");
            }
            number=2;
            System.out.println("condition2 被唤醒!!!!!!");
            condition2.signal();
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"----释放锁-----");
           // lock.unlock();
        }
    }

    public void conditionTwoAdd(){
        lock.lock();
        try {
            while (number!=2){
                System.out.println("-----condition2 被挂起-------");
                condition2.await();
            }
            for (int i =0;i<5;i++){
                System.out.println("condition2执行5次");
            }
            number=3;
            System.out.println("condition3 被唤醒!!!!!!");
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void conditionThreeAdd(){
        lock.lock();
        try {
            while (number!=3){
                System.out.println("-------condition3 被挂起-------");
                condition3.await();
            }
            for (int i =0;i<5;i++){

                System.out.println("condition3  执行5次");
            }
            number=1;
            System.out.println("condition1 被唤醒!!!!!!");
            condition1.signal();
        }catch (Exception e){
         e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
