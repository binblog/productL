package http.resource.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

class MyX509TrustManager implements X509TrustManager {
    /*
     * The default X509TrustManager returned by SunX509.  We'll delegate
     * decisions to it, and fall back to the logic in this class if the
     * default X509TrustManager doesn't trust it.
     */
    X509TrustManager sunJSSEX509TrustManager;
    MyX509TrustManager() throws Exception {
        // create a "default" JSSE X509TrustManager.
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("C:/Users/bin.liang/Desktop/snakeoil2_070531/aaa.keystore"),
                "123456".toCharArray());
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        tmf.init(ks);
        TrustManager tms [] = tmf.getTrustManagers();
        /*
         * Iterate over the returned trustmanagers, look
         * for an instance of X509TrustManager.  If found,
         * use that as our "default" trust manager.
         */
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
                sunJSSEX509TrustManager = (X509TrustManager) tms[i];
                return;
            }
        }
        /*
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        throw new Exception("Couldn't initialize");
    }
    /*
     * Delegate to the default trust manager.
     */

    /*
     * Delegate to the default trust manager.
     */


    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
        sunJSSEX509TrustManager.checkClientTrusted(x509Certificates, s);
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
        sunJSSEX509TrustManager.checkServerTrusted(x509Certificates, s);
    }

    /*
         * Merely pass this through.
         */
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return sunJSSEX509TrustManager.getAcceptedIssuers();
    }
}

/**
 * Created by bin.liang on 2016/12/13.
 */
public class HttpsTest {

    @Test
    public void test() {
        HttpClient defaultClient = new HttpClient();
        try {   // 169.254.137.76
            defaultClient.getMethod("https://54.70.215.146:8443/");

        } catch (Exception e) {

        }
    }

    public final static void main(String[] args) throws Exception {


//        System.setProperty("javax.net.ssl.trustStore", "C:/Users/bin.liang/Desktop/snakeoil2_070531/aaa.keystore");
//        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        // 加载证书
        KeyStore ks = null;

        InputStream in = new FileInputStream("C:/Users/bin.liang/Desktop/snakeoil2_070531/aaa.keystore");
        ks = KeyStore.getInstance("JKS");
        ks.load(in, "123456".toCharArray());


        // 实例化信任库
        TrustManagerFactory trustManagerFactory = null;

        trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ks);


        // 初始化信任库
        SSLContext sslContext = null;

        sslContext = SSLContext.getInstance("TLSv1");   //TLSv1为目前常用协议
        TrustManager[] tm = { new MyX509TrustManager() };
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new java.security.SecureRandom());



        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {

            HttpGet httpget = new HttpGet("https://54.70.215.146:8443/");

            System.out.println("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
