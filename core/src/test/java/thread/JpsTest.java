package thread;

/**
 * Created by bin.liang on 2016/12/12.
 */
public class JpsTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                for(int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() +  " run ...");
                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.setName("thread1");
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.setName("thread2");
        thread2.start();
    }
}
