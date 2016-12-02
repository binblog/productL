package basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bin.liang on 2016/11/17.
 */
public class StrInternTest {
    @Test
    public  void test() {
        String a = new String("hello world");
        String b = new String("hello world");

        String c = a.intern();

        Assert.assertEquals(a ,b);
        Assert.assertEquals(c , a);
    }

    @Test
    public  void test2() {
        String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }
}
