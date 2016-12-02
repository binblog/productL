package spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bin.liang on 2016/11/26.
 */
public class EmailEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    public String address;
    public String text;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public void print(){
        System.out.println("hello spring event!");
    }
}
