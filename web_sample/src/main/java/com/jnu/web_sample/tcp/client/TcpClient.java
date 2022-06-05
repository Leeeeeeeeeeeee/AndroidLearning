package com.jnu.web_sample.tcp.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {

    private String mServerIp = "172.26.112.46";
    private int mPort = 9090;

    private Scanner mScanner;

    public TcpClient() {
        mScanner = new Scanner(System.in);
        mScanner.useDelimiter("\n");
    }

    public void start() {
        try {
            Socket socket = new Socket(mServerIp, mPort);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            //输出服务端发送的数据
            new Thread() {
                @Override
                public void run() {
                    try {
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            while (true) {
                String msg = mScanner.next();
                bw.write(msg);
                bw.newLine();
                bw.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpClient().start();
    }
}
