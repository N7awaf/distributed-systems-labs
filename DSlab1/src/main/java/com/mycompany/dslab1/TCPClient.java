package com.mycompany.dslab1;

/**
 *
 * @author nawaf
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws Exception {
        System.out.println("Type your input ");

        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();

        String serverHostName = "localhost";
        int serverPort = 9876;

        Socket clientSocket = new Socket(serverHostName, serverPort);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes(sentence + "\n");

        String modSentence = inFromServer.readLine();

        System.out.println("The updated message is " + modSentence);
        System.out.println("Server port: " + clientSocket.getPort());
        System.out.println("Server IP address: " + clientSocket.getInetAddress().getHostAddress());
        System.out.println("Server host name: " + clientSocket.getInetAddress().getHostName());

        System.out.println("client port number: " + clientSocket.getLocalPort());

        clientSocket.close();
    }
}