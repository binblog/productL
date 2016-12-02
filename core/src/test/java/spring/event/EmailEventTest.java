package spring.event;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bin.liang on 2016/11/26.
 */
public class EmailEventTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        EmailEvent event = new EmailEvent("hello","boylmx@163.com","this is a email text!");
        context.publishEvent(event);
    }
}
