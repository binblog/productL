package sore.shell;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class Shell {

    private int[] arr;

    public Shell(int[] arr) {
        this.arr = arr;
    }

    public void sort() throws InterruptedException {
        int k = 1; // 增量
        while (k < arr.length / 3) {
            k = k * 3 + 1; // 采用Knuth's increments: 1, 4, 13, 40, 121, ...
        }

        System.out.println(k);
        ExecutorService service = Executors.newFixedThreadPool(4);
        for(; k >= 1; k /= 3) {

            CountDownLatch latch = new CountDownLatch(k);

            for(int h = 0; h < k; h++) {

                service.execute(new SubSort(arr, h, k, latch));
            }
            latch.await();
        }
        service.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[10000000];

        Random random = new Random();
        for(int i = 1; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }

        System.out.println();
        long startT = System.currentTimeMillis();
        Shell sort = new Shell(arr);
        sort.sort();
        System.out.println(System.currentTimeMillis() - startT);

        System.out.println();

        for(int i = 1; i < arr.length; i++) {
            if(arr[i - 1] > arr[i]) {
                System.out.println("err........");
            }
        }


    }
}
