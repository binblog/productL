package spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by bin.liang on 2016/11/26.
 */
public class EmailListener implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof EmailEvent){
            EmailEvent emailEvent = (EmailEvent)event;
            emailEvent.print();
            System.out.println("the source is:"+emailEvent.getSource());
            System.out.println("the address is:"+emailEvent.address);
            System.out.println("the email's context is:"+emailEvent.text);
        }

    }
}
