package com.ljw.collection_qs;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collection_qs {
    public static void main(String[] args) {
       new ArrayList<>();
       new Vector<>();
       Map map = new ConcurrentHashMap();
        List list = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());
        new Vector<>();
        for (int i= 0; i<=30; i++){
            new Thread(()->{
               list.add(UUID.randomUUID());
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
