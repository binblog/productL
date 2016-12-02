package bean;

import org.junit.Test;

/**
 * Created by bin.liang on 2016/11/30.
 */
public class BlogTest {

    @Test
    public void test() {
        Blog blog = new Blog();
        blog.setTitle("hello");
        System.out.println(blog.getTitle());
    }
}
