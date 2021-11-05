package com.ljw.threadpool;

import java.util.concurrent.*;

public class Callable_qs {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Callable callable =new CallableTest();
        FutureTask futureTask =new FutureTask(callable);
        Thread thread = new Thread(futureTask,"future");
        Thread thread2 = new Thread(futureTask,"future2");
        thread.start();
        thread2.start();
        System.out.println(futureTask.get());


    }
}

class CallableTest implements Callable{

    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"来了");
        return 4396;
    }
}