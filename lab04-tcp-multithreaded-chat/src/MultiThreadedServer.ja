/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab4;

/**
 *
 * @author nawaf
 */
import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        int serverPort = 8044;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Server is listening on port: " + serverSocket.getLocalPort());

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("chat with a Client, client's port number: " + connectionSocket.getPort());

            new Thread(() -> {
                try {
                    BufferedReader msgFromClient = new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
                    DataOutputStream msgToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

                    String clientMsg, serverMsg;

                    while ((clientMsg = msgFromClient.readLine()) != null) {
                        System.out.println("Client: " + clientMsg);

                        System.out.print("Server: ");
                        serverMsg = userInput.readLine();
                        msgToClient.writeBytes(serverMsg + " msg from MTS\n");
                    }

                    System.out.println("chat has stopped by the client on port " + connectionSocket.getPort());
                    connectionSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
