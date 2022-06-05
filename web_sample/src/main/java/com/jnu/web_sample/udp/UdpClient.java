package com.jnu.web_sample.udp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient {

    private static final String TAG = "<UdpClient";
    private String mServerIp = "172.26.112.46";
    private InetAddress mServerAddress;
    private int mServerPort = 7777;
    private DatagramSocket mSocket;
    private Scanner mScanner;

    public UdpClient() {
        try {
            mServerAddress = InetAddress.getByName(mServerIp);
            mSocket = new DatagramSocket();
            mScanner = new Scanner(System.in);
            mScanner.useDelimiter("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                String clientMsg = mScanner.next();
                byte[] clientMsgBytes = clientMsg.getBytes();
                DatagramPacket clientPacket = new DatagramPacket(clientMsgBytes, clientMsgBytes.length, mServerAddress, mServerPort);
                mSocket.send(clientPacket);
                
                byte[] buf = new byte[1024];
                DatagramPacket serverPacket = new DatagramPacket(buf,buf.length);
                mSocket.receive(serverPacket);


                InetAddress address = serverPacket.getAddress();
                int port = serverPacket.getPort();
                byte[] data = serverPacket.getData();
                String serverMsg = new String(data, 0, serverPacket.getLength());
                System.out.println( "address:" + address + " port:" + port + " msg:" + serverMsg);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args){
        new UdpClient().start();
    }
}
