package sore;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class InsertSort implements Runnable{

    private int[] arr;

    private int end;
    private int start;

    private CountDownLatch latch ;
    public InsertSort(int[] arr, int start, int end, CountDownLatch latch) {
        this.arr = arr;

        this.start = start;
        this.latch = latch;
        if(end >= arr.length) {
            this.end = arr.length - 1;
        } else {
            this.end = end;
        }

    }


    public void sort() {
        for(int i = 1; i <= end; i++) {

            int temp = arr[i];
            int j = i - 1;

            for(; j >= start && temp < arr[j]; j--) {

                    arr[j + 1] = arr[j];


            }
            arr[j + 1] = temp;

        }



    }

    public static void main(String[] args) throws InterruptedException {
//        int[] arr = new int[]{3,2,9,4,0,4,8};

//        InsertSort sort = new InsertSort(arr, 2, 6);
//        sort.sort();
//
//        Arrays.stream(arr).forEach(System.out::println);

        int[] arr = new int[1000000];

        Random random = new Random();
        for(int i = 1; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }

        long st = System.currentTimeMillis();
        InsertSort sort = new InsertSort(arr, 0, arr.length, null);
        sort.sort();
        System.out.println(System.currentTimeMillis() - st);


//
//        ExecutorService service = Executors.newFixedThreadPool(4);
//
//        int rag = arr.length / 4;
//
//        int start  = 0;
//        CountDownLatch latch = new CountDownLatch(4);
//        long st = System.currentTimeMillis();
//        for(int i = 0; i < 4; i++) {
//            System.out.println(start + " : " + rag);
//            service.execute(new InsertSort(arr, start, rag, latch));
//            start = rag + 1;
//            rag *= 2;
//        }
//
//        service.shutdown();
//        latch.await();
//        System.out.println(System.currentTimeMillis() - st);


    }

    @Override
    public void run() {
        for(int i = 1; i <= end; i++) {
            int temp = arr[i];
            int j = i - 1;

            for(; j >= start && temp < arr[j]; j--) {

                arr[j + 1] = arr[j];


            }
            arr[j + 1] = temp;

        }
        latch.countDown();
    }



}
