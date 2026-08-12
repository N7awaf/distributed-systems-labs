
package com.mycompany.dslab2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {

        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Type your sentence:");
        String sentence = inFromUser.readLine();

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress serverIP = InetAddress.getByName("localhost");

        byte[] sendData = sentence.getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, serverIP, 9876);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence =
                new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Output message:\n" + modifiedSentence);

        clientSocket.close();
    }
}

