package com.ljw;

import com.ljw.lock_qs.City_Enum;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
	// write your code here
      int[] a = new int[3];
      a[0] = 5;
      a[1] = 3;
      a[2] = 4;
        Arrays.sort(a);
        System.out.println(a[0]);




    }
}
