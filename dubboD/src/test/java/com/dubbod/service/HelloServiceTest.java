package com.dubbod.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bin.liang on 2016/12/16.
 */
public class HelloServiceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext(new String[]{"classpath:META-INF/spring/dubbo-client.xml"});
        ctx.start();

        HelloService service = (HelloService)ctx.getBean("helloService");

        for(int i = 0; i < 100; i++)
                System.out.println(service.hello("bin"));

//        try {
//            Thread.sleep(1000 * 30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
