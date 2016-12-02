package http.resource.httpclient;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by bin.liang on 2016/11/30.
 */
public class DefaultClientTest {

    @Test
    public void testPingMethod() {
        DefaultClient defaultClient = new DefaultClient();
        try {
            defaultClient.getMethod("http://localhost:8080/rbatis/blog/ping");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMethod() {
        DefaultClient defaultClient = new DefaultClient();
        try {
            defaultClient.getMethod("http://localhost:8080/rbatis/blog/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPostMethod() {
        DefaultClient defaultClient = new DefaultClient();

        try {
            defaultClient.post("http://localhost:8080/rbatis/blog", "{\"title\":\"hello, service\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteMethod() {
        DefaultClient defaultClient = new DefaultClient();

        try {
            defaultClient.delete("http://localhost:8080/rbatis/blog/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testPut() {
        DefaultClient defaultClient = new DefaultClient();

        try {
            defaultClient.put("http://localhost:8080/rbatis/blog/1", "{\"title\":\"hello, I need put\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
