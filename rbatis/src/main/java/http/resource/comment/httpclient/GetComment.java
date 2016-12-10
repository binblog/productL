package http.resource.comment.httpclient;

import http.resource.comment.HttpComment;
import http.resource.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by bin on 2016/12/4.
 */
public class GetComment implements HttpComment {




    public HttpResponse execute(String url, String contentType, byte[] params) {
        HttpGet httpGet = new HttpGet(url);

        try {
            return execute(url, httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }



    private HttpResponse execute(String url, HttpRequestBase requestBase) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();


        CloseableHttpResponse response1 = httpclient.execute(requestBase);


        HttpResponse response = new HttpResponse();
        try {


            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body   // �߼�����


//            String result = EntityUtils.toString(entity1);

            response.setCode(response1.getStatusLine().getStatusCode());
            response.setContent(EntityUtils.toByteArray(entity1));


            EntityUtils.consume(entity1);
            return    response  ;
        } finally {
            response1.close();
        }



    }
}
