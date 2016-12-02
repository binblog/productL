import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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