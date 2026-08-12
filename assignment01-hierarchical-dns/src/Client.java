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
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddr = InetAddress.getLoopbackAddress();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Domain Name: ");
        String domain = input.nextLine();
        byte[] sendData = domain.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, 5000);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("IP Address: " + result);

        socket.close();
    }
}
