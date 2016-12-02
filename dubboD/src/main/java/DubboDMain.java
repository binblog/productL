import com.dubbod.service.BlogService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class DubboDMain {




    public static void main( String[] args )
    {
        System.setProperty("dubbo.application.logger", "slf4j");    // 告诉dubbo使用slf4j日志框架

//        com.alibaba.dubbo.container.Main.main(args);

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:META-INF/spring/application.xml"});

        BlogService blogService = (BlogService)ctx.getBean("blogService");

        System.out.println(blogService);

        System.out.println(blogService.getBlog(1L));


    }
}
