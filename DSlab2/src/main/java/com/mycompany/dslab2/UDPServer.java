
package com.mycompany.dslab2;

import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {

        System.out.println("UDP Server is running ###");

        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String sentence = new String(
                    receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Received: " + sentence);

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, clientIP, clientPort);

            serverSocket.send(sendPacket);
        }
    }
}
