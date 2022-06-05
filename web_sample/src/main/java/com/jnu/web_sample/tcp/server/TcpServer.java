package com.jnu.web_sample.tcp.server;

import com.jnu.web_sample.tcp.client.TcpClient;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090);
            MsgPool.getInstance().start();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("ip:" + socket.getInetAddress().getHostAddress() + " port:" + socket.getPort() + " is online...");
                ClientTask clientTask = new ClientTask(socket);
                MsgPool.getInstance().addMsgComingListener(clientTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpServer().start();
    }
}
