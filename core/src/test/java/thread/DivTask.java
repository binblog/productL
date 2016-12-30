package thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bin.liang on 2016/12/27.
 */
public class DivTask implements  Runnable{
    int a, b;
    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        try {
            double re = a / b;
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        for(int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));
        }

    }
}
