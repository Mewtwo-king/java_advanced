package com.ljw.threadpool;

import java.util.concurrent.*;

public class ThreadPoolPolicy_qs {
    public static void main(String[] args) {
        ExecutorService service =new ThreadPoolExecutor(2,3,5L, TimeUnit.SECONDS,
               new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        try {
            System.out.println(System.getSecurityManager());
            for (int i =1 ;i<=10;i++){
                service.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"执行任务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            service.shutdown();
        }

    }
}
