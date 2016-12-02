package memory;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin.liang on 2016/11/28.
 */
public class OOMTest {

    @Test
    public void  basic() {
        List<Object> list = new ArrayList<Object>();

        String str = "Hello, Java";
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));

        System.out.println("start : " + sr.get());
        try {
            while (true) {
                list.add(new Object());

            }
        } catch (Throwable e) {
            e.printStackTrace();

            System.out.println(sr.get() +  "  // *** size : " + list.size());
        }
    }

    @Test
    public void weakTest() {
        WeakReference<String> weakReference = new WeakReference<String>(new String("hello"));

        System.out.println(weakReference.get());

        System.gc();

        System.out.println(weakReference.get());
    }

    @Test
    public void phantomTest() {
//        ReferenceQueue<String> queue = new ReferenceQueue<String>();
//        byte[] bytes = new byte[1024 * 1024 * 5];   //  5m
//        byte[] bytes = new byte[1024 * 1024 * 5];
//        PhantomReference<byte[]> pr = new PhantomReference(bytes, queue);

//        List<Object> list = new ArrayList<Object>();
        /*while (true) {
            if(pr.equals(queue.poll())) {
                System.out.println("准备回收");
            }
            list.add(new Object());
        }*/

        System.out.println("hello, world");
    }
}
