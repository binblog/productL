package spring.bean;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by bin.liang on 2016/11/24.
 */
public class BeanFactoryTest {

    @Test
    public void test() {


        BeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
//        ApplicationContext content = new ClassPathXmlApplicationContext("spring.xml");
        Blog bean = (Blog)xmlBeanFactory.getBean("blog");

        Assert.assertEquals(bean.getTitle(), "hello spring");
    }
}
