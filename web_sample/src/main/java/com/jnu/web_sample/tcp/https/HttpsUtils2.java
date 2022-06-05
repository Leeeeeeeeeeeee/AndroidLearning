package com.jnu.web_sample.tcp.https;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class HttpsUtils2 {

    public interface HttpListener {
        void onSuccess(String content);

        void onFail(Exception ex);
    }

    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void doGet(Context context, final String urlStr, HttpUtils.HttpListener listener) {

        new Thread() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    X509Certificate serverCert = getcert(context);

                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null);
                    keyStore.setCertificateEntry("srca",serverCert);
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, trustManagers, new SecureRandom());
                    conn.setSSLSocketFactory(sslContext.getSocketFactory());

                    conn.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            //校验域名
                            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                            return defaultHostnameVerifier.verify("12306.cn", sslSession);
                        }
                    });
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    conn.connect();

                    is = conn.getInputStream();
                    byte[] buf = new byte[2048];
                    int len = -1;
                    StringBuilder content = new StringBuilder();
                    while ((len = is.read(buf)) != -1) {
                        content.append(new String(buf, 0, len));
                    }
                    mUIHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(content.toString());
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFail(e);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private static X509Certificate getcert(Context context) {
        try {
            //证书需要自己下载
            InputStream is = context.getAssets().open("srca.cer");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificateFactory.generateCertificate(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
