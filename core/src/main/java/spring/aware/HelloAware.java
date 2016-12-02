package spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by bin.liang on 2016/11/26.
 */
public class HelloAware implements BeanNameAware,BeanFactoryAware {
    public HelloAware() {

    }

    private BeanFactory bf;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
        this.bf = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("回调setBeanName方法  id属性是"+name);
    }

    public BeanFactory getBf() {
        return bf;
    }
}
