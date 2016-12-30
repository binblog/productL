package sore.shell;

import java.util.concurrent.CountDownLatch;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class SubSort implements Runnable {

    private int[] arr;
    private int h;
    private int k;

    private CountDownLatch latch;

    public SubSort(int[] arr, int h , int k, CountDownLatch latch) {
        this.arr = arr;
        this.h = h;
        this.k = k;
        this.latch = latch;
    }


    @Override
    public void run() {
        for (int i = h + k; i < arr.length; i += k) {
            int temp = arr[i];
            int j = i - k;
            while (j >= h  && temp < arr[j]) {

                arr[j + k] = arr[j];
                j -= k;
            }
            arr[j + k] = temp;
        }

        latch.countDown();
//        System.out.println("finish");
    }
}
