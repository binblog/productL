package sore.ran;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bin.liang on 2016/12/29.
 */
public class Mul implements  Runnable {

    private CountDownLatch latch ;

    public Mul(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        int[] arr = new int[1000];
        for(int i = 0; i < arr.length ; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);

        long st = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(4);

        for(int i = 0; i < 10; i++) {
            service.execute(new Mul(latch));
        }
        latch.await();

        System.out.println(System.currentTimeMillis() - st);

        service.shutdown();
    }
}
