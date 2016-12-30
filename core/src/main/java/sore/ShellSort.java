package sore;

import java.util.Random;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class ShellSort {

    public int[] arr;

    public ShellSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int k = 1; // 增量
        while (k < arr.length / 3) {
            k = k * 3 + 1; // 采用Knuth's increments: 1, 4, 13, 40, 121, ...
        }

        System.out.println(k);
        for(; k >= 1; k /= 3) {
//            System.out.println(" k : " + k);
            for(int h = 0; h < k; h++) {
//                System.out.println(" h : " + h);

                sortSubArr(arr, h, k);
            }
        }
    }

    public  void sortSubArr(int[] arr, int h, int k) {
        for (int i = h + k; i < arr.length; i += k) {
            int temp = arr[i];
            int j = i - k;
            while (j >= h  && temp < arr[j]) {
                arr[j + k] = arr[j];
                j -= k;
            }
            arr[j + k] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{3,2,9,4,0,5,8};


        int[] arr = new int[10000000];

        Random random = new Random();
        for(int i = 1; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }
//        Arrays.stream(arr).forEach((x) -> {
//            System.out.print(x + ", ");
//        });
        System.out.println();

        ShellSort sort = new ShellSort(arr);

        long startT = System.currentTimeMillis();
        sort.sort();
        System.out.println(System.currentTimeMillis() - startT);

//        Arrays.stream(arr).forEach((x) -> {
//            System.out.print(x + ", ");
//        });
        System.out.println();



        for(int i = 1; i < arr.length; i++) {
            if(arr[i - 1] > arr[i]) {
                System.out.println("err........");
            }
        }


    }

}
