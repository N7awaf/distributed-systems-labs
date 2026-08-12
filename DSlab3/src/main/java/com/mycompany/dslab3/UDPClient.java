/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab3;

/**
 *
 * @author nawaf
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(62000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter website: ");
        String host = input.readLine();

        byte[] sendData = host.getBytes();

        InetAddress serverAddress = InetAddress.getByName("localhost");

        DatagramPacket request = new DatagramPacket(
                sendData,
                sendData.length,
                serverAddress,
                5300
        );

        socket.send(request);

        byte[] buffer = new byte[1024];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String ip = new String(response.getData(), 0, response.getLength());
        System.out.println("IP Address: " + ip);

        socket.close();
    }
}