package http.resource.httpclient;

import http.resource.comment.HttpComment;
import http.resource.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by bin.liang on 2016/12/6.
 */
public class PostComment implements HttpComment {
    @Override
    public HttpResponse execute(String url, String contentType, byte[] params) {

        HttpEntityEnclosingRequestBase requestBase = new HttpPost(url);

        CloseableHttpClient httpclient = HttpClients.createDefault();
//        requestBase.setHeader("Content-Type", "application/json");
        if(MediaType.APPLICATION_OCTET_STREAM.equals(contentType)) {
            requestBase.setHeader("Content-Type", contentType);
        }


//        HttpEntity stringEntity = new StringEntity(json);
        HttpEntity requestEntity = new ByteArrayEntity(params);

        requestBase.setEntity(requestEntity);

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(requestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpResponse resultResponse = new HttpResponse();

        resultResponse.setCode(response.getStatusLine().getStatusCode());
        HttpEntity entity1 = response.getEntity();
        try {
            resultResponse.setContent(EntityUtils.toByteArray(entity1));
            EntityUtils.consume(entity1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultResponse;
    }


}
