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
import java.util.HashMap;

public class LocalServer {

    private static HashMap<String, String> table = new HashMap<>();

    public static void main(String[] args) throws Exception {
        table.put("www.psau.edu.sa", "183.10.15.1");
        table.put("www.ksu.edu.sa", "183.7.23.55");

        DatagramSocket socket = new DatagramSocket(5000);
        System.out.println("Local DNS Server is running...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            new Thread(() -> {
                try {
                    String domain = new String(request.getData(), 0, request.getLength());
                    String ip = table.get(domain);

                    if (ip == null) {
                        ip = contactRoot(domain);
                        if (!ip.equals("unknown")) {
                            table.put(domain, ip);
                        }
                    }

                    byte[] sendData = ip.getBytes();
                    DatagramPacket response = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
                    socket.send(response);
                } catch (Exception e) {
                }
            }).start();
        }
    }

    private static String contactRoot(String domain) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress rootAddr = InetAddress.getLoopbackAddress();
        byte[] sendData = domain.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, rootAddr, 6000);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        socket.close();
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}
