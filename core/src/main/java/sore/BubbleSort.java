package sore;

import java.util.Arrays;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class BubbleSort {

    private int[] arr;
    public BubbleSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        for(int i = arr.length - 1; i > 0; i--) {
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,9,4,0,8};

        BubbleSort sort = new BubbleSort(arr);
        sort.sort();

        Arrays.stream(arr).forEach(System.out::println);
    }
}

