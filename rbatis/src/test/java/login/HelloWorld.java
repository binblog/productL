package login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by bin.liang on 2016/12/2.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.info("Hello World");


        Thread thread = new Thread(new Runnable() {

            public void run() {
                Logger logger = LoggerFactory.getLogger(HelloWorld.class);
                logger.info("Hello World on child thread");
            }
        });

        thread.setName("child thread");
        thread.start();
    }

}