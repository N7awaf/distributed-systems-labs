package com.mycompany.dslab1;

/**
 *
 * @author nawaf
 */
import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {
        int port = 9876;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running on port " + port);

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("-- client's IP address: " + connectionSocket.getInetAddress().getHostAddress());
            System.out.println("-- client's port number: " + connectionSocket.getPort());

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            String sentence = inFromClient.readLine();
            String capitalizedSentence = sentence.toUpperCase() + "\n";

            outToClient.writeBytes(capitalizedSentence);

            connectionSocket.close();
        }
    }
}
