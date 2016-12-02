package basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bin.liang on 2016/11/29.
 */
public class AssertTest {

    @Test
    public void test1() {
        int i = 1 + 1;
        Assert.assertEquals(i , 2);
    }
}
