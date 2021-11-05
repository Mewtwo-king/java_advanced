package com.ljw.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool_qs {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(5);
        try {

            for (int i = 0;i<10;i++){
                service.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"处理任务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            service.shutdown();
        }

    }
}
