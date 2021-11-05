package com.ljw.lock_qs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        myCache cache = new myCache();
        for (int i = 0;i<5;i++){
            final  int key = i;
            new Thread(()->{
                try {
                    cache.put(String.valueOf(key),key);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 0;i<5;i++){
            final  int key = i;
            new Thread(()->{
                try {
                    cache.get(String.valueOf(key));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

class  myCache{
    private volatile Map<String,Object> map =new HashMap();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    public  void get(String key) throws InterruptedException {
        lock.readLock().lock();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+" 读数据"+map.get(key));
        map.get(key);
        lock.readLock().unlock();
    }

    public  void put(String key,Object o) throws InterruptedException {
        lock.writeLock().lock();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+" 写入: "+o);
        map.put(key,o);
        lock.writeLock().unlock();
    }
}
