package service.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.blog.BlogService;

/**
 * Created by bin.liang on 2016/11/22.
 */
public class BlogServiceTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:mybatis-spring.xml"});

        BlogService service = ctx.getBean("blogService", BlogService.class);
        System.out.println(service.selectBlog(1));

    }
}
