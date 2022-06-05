package com.jnu.web_sample.tcp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTask extends Thread implements MsgPool.MsgComingListener {
    private Socket mSocket;
    private InputStream mIs;
    private OutputStream mOs;

    public ClientTask(Socket socket) {
        try {
            mSocket = socket;
            mIs = socket.getInputStream();
            mOs = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(mIs));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println("read = " + line);
                //转发消息到其他客户端
                MsgPool.getInstance().sendMsg(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMsgComing(String msg) {
        try {
            mOs.write(msg.getBytes());
            mOs.write("\n".getBytes());
            mOs.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
