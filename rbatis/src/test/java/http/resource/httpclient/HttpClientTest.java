package http.resource.httpclient;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by bin.liang on 2016/11/30.
 */
public class HttpClientTest {

    @Test
    public void testPingMethod() {
        HttpClient defaultClient = new HttpClient();
        try {   // 169.254.137.76
            defaultClient.getMethod("http://127.0.0.1:8080/rbatis/blog/ping");
        } catch (IOException e) {

        }
    }

    @Test
    public void testGetMethod() {
        HttpClient defaultClient = new HttpClient();
        try {
            defaultClient.getMethod("http://localhost:8080/rbatis/blog/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPostMethod() {
        HttpClient defaultClient = new HttpClient();

        try {
            defaultClient.post("http://localhost:8080/rbatis/blog", "{\"title\":\"hello, service\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteMethod() {
        HttpClient defaultClient = new HttpClient();

        try {
            defaultClient.delete("http://localhost:8080/rbatis/blog/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testPut() {
        HttpClient defaultClient = new HttpClient();

        try {
            defaultClient.put("http://localhost:8080/rbatis/blog/1", "{\"title\":\"hello, I need put\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testPostFile() {

//        System.out.println(System.getProperty("user.dir"));
        File file = new File("1.png");
        System.out.println(file.getAbsolutePath());

        HttpClient defaultClient = new HttpClient();

        try {
            defaultClient.postFile("http://localhost:8080/rbatis/blog/attachment", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
