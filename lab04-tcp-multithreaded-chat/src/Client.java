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

public class Client {
    public static void main(String[] args) throws Exception {
        int serverPort = 8044;
        
        try {
            Socket clientSocket = new Socket("localhost", serverPort);
            
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Starting chatting with the server, client's port is " + clientSocket.getLocalPort());
            
            String clientMsg, serverMsg;

            while (true) {
                clientMsg = userInput.readLine();
                
                if (clientMsg.equalsIgnoreCase("stop")) break;

                outToServer.writeBytes(clientMsg + " msg from C1\n");

                serverMsg = inFromServer.readLine();
                System.out.println(serverMsg);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
