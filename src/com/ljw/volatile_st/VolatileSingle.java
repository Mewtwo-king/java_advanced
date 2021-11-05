package com.ljw.volatile_st;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VolatileSingle {
    private static volatile VolatileSingle single =null;
    private  VolatileSingle(){
        System.out.println(Thread.currentThread().getName()+"构造是咧");
    }
    public  static VolatileSingle getInstence(){
        List list = new CopyOnWriteArrayList();
        synchronized (VolatileSingle.class){
            if (single == null){
                synchronized (VolatileSingle.class){
                    single = new VolatileSingle();
                }
            }
        }
//        if (single == null){
//            single = new VolatileSingle();
//        }
        return single;
    }

    public static void main(String[] args) {
        for (int i =0 ; i<10;i++){
            new Thread(()->{
                VolatileSingle.getInstence();
            },"nn"+i).start();
        }
    }

}
