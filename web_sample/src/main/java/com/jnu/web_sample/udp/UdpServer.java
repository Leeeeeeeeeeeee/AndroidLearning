package com.jnu.web_sample.udp;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UdpServer {
    private static final String TAG = "<UdpServer";

    private InetAddress mInetAddress;

    private int mPort = 7777;

    private DatagramSocket mSocket;

    private Scanner mScanner;

    public UdpServer() {
        try {
            mInetAddress = InetAddress.getLocalHost();
            mSocket = new DatagramSocket(mPort, mInetAddress);
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                //udp在每次通信的过程中，数据包大小有限制
                byte[] buf = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                mSocket.receive(receivedPacket);

                InetAddress address = receivedPacket.getAddress();
                int port = receivedPacket.getPort();
                byte[] data = receivedPacket.getData();
                String clientMsg = new String(data, 0, receivedPacket.getLength());
                System.out.println( "address:" + address + " port:" + port + " msg:" + clientMsg);

                String returnMsg = mScanner.next();
                byte[] returnMsgBytes = returnMsg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(returnMsgBytes, returnMsgBytes.length, receivedPacket.getSocketAddress());
                mSocket.send(sendPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new UdpServer().start();
    }
}
