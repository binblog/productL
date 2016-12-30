package https;


import org.junit.Test;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;

/**
 * Created by bin.liang on 2016/12/26.
 */
public class Get {

    @Test
    public void testGet() throws Exception {
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

        URL url = new URL("https://54.70.215.146:8443/");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(ctx.getSocketFactory());


        con.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                // ip address of the service URL(like.23.28.244.244)
//                if (hostname.equals("23.28.244.244"))
//                    return true;
//                return false;

                return true;
            }
        });

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);

        // 设置请求方式（GET/POST）
        con.setRequestMethod("GET");

        con.connect();

        // 将返回的输入流转换成字符串
        InputStream inputStream = con.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        inputStream = null;
        con.disconnect();

        System.out.println(buffer.toString());
    }
}
