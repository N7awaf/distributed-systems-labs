/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab3;

/**
 *
 * @author nawaf
 */
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5300);
        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            String host = new String(request.getData(), 0, request.getLength()).trim();

            InetAddress ip = InetAddress.getByName(host);
            String ipAddress = ip.getHostAddress();

            byte[] responseData = ipAddress.getBytes();

            DatagramPacket response = new DatagramPacket(
                    responseData,
                    responseData.length,
                    request.getAddress(),
                    request.getPort()
            );

            socket.send(response);
        }
    }
}
