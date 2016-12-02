package spring.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by bin.liang on 2016/11/26.
 */
public class HelloInit implements InitializingBean,DisposableBean {

    public HelloInit() {

    }

    @Override
    public void destroy() throws Exception {

        System.out.println("I'm  init  method  using implements InitializingBean interface....");

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("I'm  init  method  using implements DisposableBean interface....");

    }
}
