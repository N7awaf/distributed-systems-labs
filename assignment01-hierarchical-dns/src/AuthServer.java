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

public class AuthServer {

    private static HashMap<String, String> table = new HashMap<>();

    public static void main(String[] args) throws Exception {
        table.put("www.facebook.com", "212.15.17.1");
        table.put("www.google.com", "212.40.3.7");
        table.put("www.x.com", "212.55.149.156");

        DatagramSocket socket = new DatagramSocket(7000);
        System.out.println("Authoritative Server is running...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            new Thread(() -> {
                try {
                    String domain = new String(request.getData(), 0, request.getLength());
                    String ip = table.getOrDefault(domain, "unknown");

                    byte[] sendData = ip.getBytes();
                    DatagramPacket response = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
                    socket.send(response);
                } catch (Exception e) {
                }
            }).start();
        }
    }
}
