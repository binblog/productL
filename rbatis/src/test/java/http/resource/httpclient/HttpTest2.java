package http.resource.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by bin.liang on 2016/12/13.
 */
public class HttpTest2 {

    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        String keyStoreFile = "C:/Users/bin.liang/Desktop/snakeoil2_070531/aaa.keystore";
        String password = "123456";
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream in = new FileInputStream(keyStoreFile);
        ks.load(in, password.toCharArray());

        System.out.println(KeyStore.getDefaultType().toString());
        System.out.println(TrustManagerFactory.getDefaultAlgorithm().toString());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        SSLContext ctx = SSLContext.getInstance("TLSv1");
        ctx.init(null, tmf.getTrustManagers(), null);



        LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);

        String url = "https://54.70.215.146:8443/";

        /**
         * Return the page with content:
         *  401 Authorization Required
         */


        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)

                .build();

        HttpGet request = new HttpGet(url);
//        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");

        CloseableHttpResponse response = httpclient.execute(request);

        HttpEntity en = response.getEntity();
        System.out.println(EntityUtils.toString(en));
    }


}
