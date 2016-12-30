package sore.ran;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class Single {


    public void run() throws InterruptedException {
        int[] arr = new int[4000];
        for(int i = 0; i < arr.length ; i++) {
            Thread.sleep(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Single single = new Single();

        long st = System.currentTimeMillis();
        single.run();
        System.out.println(System.currentTimeMillis() - st);
    }
}
