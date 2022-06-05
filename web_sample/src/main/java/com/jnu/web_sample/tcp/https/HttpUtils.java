package com.jnu.web_sample.tcp.https;

import android.os.Handler;
import android.os.Looper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class HttpUtils {

    public interface HttpListener {
        void onSuccess(String content);

        void onFail(Exception ex);
    }

    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void doGet(final String urlStr, HttpListener listener) {

        new Thread() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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

}
