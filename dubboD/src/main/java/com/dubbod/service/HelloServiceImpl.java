package com.dubbod.service;

/**
 * Created by bin.liang on 2016/12/16.
 */
public class HelloServiceImpl  implements HelloService  {
    public String hello(String user) {
        System.out.println(" user : " + user + " say hello !");


        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello ! " + user;
    }
}
