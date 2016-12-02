package http.resource;

import example.service.BlogService;
import org.junit.Test;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ConfigurationTest {

    @Test
    public void test() {

        // resourceFactory
        Configuration configuration = new Configuration();

        configuration.setPreUrl("http://localhost:8080/rbatis/");
        configuration.addClass(BlogService.class);
    }
}
