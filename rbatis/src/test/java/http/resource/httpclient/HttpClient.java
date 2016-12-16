package http.resource.httpclient;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by bin.liang on 2016/11/30.
 */
public class HttpClient {

    // Content-Type, ״̬�룬 ����

    public void getMethod(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        execute(url, httpGet);
    }

    private static  CloseableHttpClient httpclient;
    private void execute(String url, HttpRequestBase requestBase) throws IOException {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

            @Override
            public long getKeepAliveDuration(HttpResponse response, org.apache.http.protocol.HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch(NumberFormatException ignore) {
                        }
                    }
                }

                return 30 * 1000;
                /*HttpHost target = (HttpHost) context.getAttribute(
                        HttpClientContext.HTTP_TARGET_HOST);
                if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
                    // Keep alive for 5 seconds only
                    return 5 * 1000;
                } else {
                    // otherwise keep alive for 30 seconds
                    return 30 * 1000;
                }*/
            }



        };
//        requestBase.setHeader("Content-Length", "100");
        if(httpclient == null)
            httpclient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy).build();


        CloseableHttpResponse response1 = httpclient.execute(requestBase);


        try {
            System.out.println(response1.getStatusLine());

            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body   // �߼�����

            System.out.println(response1.getStatusLine().getStatusCode() + "  ------------------");
            System.out.println(EntityUtils.toString(entity1));

            // and ensure it is fully consumed  �ر�
//            EntityUtils.consume(entity1);
        } finally {
//            response1.close();
        }
    }






    public void post(String url, String json) throws IOException {
        HttpEntityEnclosingRequestBase httpPost = new HttpPost(url);
        execute(url , json , httpPost);
    }

    public void postFile(String url, File file) throws IOException {
        HttpEntityEnclosingRequestBase httpPost = new HttpPost(url);

        CloseableHttpClient httpclient = HttpClients.createDefault();

//        httpPost.setHeader("Content-Type", "multipart/form-data");

//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addTextBody("name", "1_upload.png");
//        builder.addBinaryBody("file", file);
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
//        builder.addTextBody("name", "1_upload.png", ContentType.DEFAULT_BINARY);
//        HttpEntity entity = builder.build();

        // octet-stream
//        httpPost.setHeader("Content-Type", "application/octet-stream");
//        HttpEntity entity = new FileEntity(file);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        builder.addTextBody("name", "2_upload.png", ContentType.DEFAULT_BINARY);


        HttpEntity entity = builder.build();


        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            int status = response.getStatusLine().getStatusCode();

            System.out.println(response.getStatusLine());


            if(status >= 200 && status < 300) {
                HttpEntity resultEntity = response.getEntity();

                if(resultEntity == null) {
                    System.out.println("entity is null");
                } else {
                    System.out.println(EntityUtils.toString(resultEntity));
                }

            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } finally {
            httpclient.close();
        }
    }

    public  void put(String url, String  json) throws IOException {
        HttpEntityEnclosingRequestBase HttpPut = new HttpPut(url);
        execute(url , json , HttpPut);
    }

    private void execute(String url, String json, HttpEntityEnclosingRequestBase requestBase) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        requestBase.setHeader("Content-Type", "application/json");

        StringEntity stringEntity = new StringEntity(json);

        requestBase.setEntity(stringEntity);

        CloseableHttpResponse response = httpclient.execute(requestBase);

        try {
            int status = response.getStatusLine().getStatusCode();

            System.out.println(response.getStatusLine());


            if(status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();

                if(entity == null) {
                    System.out.println("entity is null");
                } else {
                    System.out.println(EntityUtils.toString(entity));
                }

            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } finally {
            httpclient.close();
        }
    }

    public void delete(String url) throws IOException {
        HttpRequestBase httpGet = new HttpDelete(url);

        execute(url, httpGet);
    }





}
