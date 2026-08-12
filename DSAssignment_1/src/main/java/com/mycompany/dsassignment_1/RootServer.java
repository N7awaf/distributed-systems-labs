/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsassignment_1;

/**
 *
 * @author nawaf
 */
import java.net.*;

public class RootServer {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);
        System.out.println("Root Server is running...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            new Thread(() -> {
                try {
                    String domain = new String(request.getData(), 0, request.getLength());
                    String ip = contactAuth(domain);

                    byte[] sendData = ip.getBytes();
                    DatagramPacket response = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
                    socket.send(response);
                } catch (Exception e) {
                }
            }).start();
        }
    }

    private static String contactAuth(String domain) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress authAddr = InetAddress.getLoopbackAddress();
        byte[] sendData = domain.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, authAddr, 7000);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        socket.close();
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}
