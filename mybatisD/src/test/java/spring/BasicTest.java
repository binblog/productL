package spring;

import domain.blog.Blog;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by bin.liang on 2016/11/22.
 */
public class BasicTest {
    @Test
    public void test() {

//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:application.xml"});

        BeanFactory ctx = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        Blog blog = ctx.getBean("blog", Blog.class);
        System.out.println(blog);

    }
}
