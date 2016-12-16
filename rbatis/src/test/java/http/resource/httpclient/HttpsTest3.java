package http.resource.httpclient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by bin.liang on 2016/12/13.
 */
public class HttpsTest3 {
    /**
     * @param jksPath   jks路径
     * @param publicPassword    jks公钥
     * @param privatePassword   jks密钥
     */
    public static SSLContext loadSSLContext(String jksPath, String publicPassword, String privatePassword)
            throws Exception {
        // 加载KeyStore
        InputStream in = new FileInputStream(jksPath);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(in, publicPassword.toCharArray());
        in.close();

        // 加载密钥
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, privatePassword.toCharArray());

        // 加载信任证书
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ks);


        // 加载SSLContext
        SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("TLSv1");   //TLSv1为目前常用协议
        sslContext.init(kmf.getKeyManagers(), trustManagerFactory.getTrustManagers(), new java.security.SecureRandom());
        return sslContext;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(loadSSLContext("C:\\Users\\bin.liang\\Desktop\\snakeoil2_070531\\ttt.keystore", "123456", "789789"));
    }
}
