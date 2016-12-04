package http.resource.comment;

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
public class GetComment implements  HttpComment {

    @Override
    public String excute(String url, Object params) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        return execute(url, httpGet);
    }

    private String execute(String url, HttpRequestBase requestBase) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();


        CloseableHttpResponse response1 = httpclient.execute(requestBase);

        try {
            System.out.println(response1.getStatusLine());

            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body   // �߼�����


            String result = EntityUtils.toString(entity1);



            // and ensure it is fully consumed  �ر�
            EntityUtils.consume(entity1);
            return      result;
        } finally {
            response1.close();
        }

    }
}
