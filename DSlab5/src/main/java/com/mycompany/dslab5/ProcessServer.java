package com.mycompany.dslab5;


import java.io.*;
import java.net.*;

public class ProcessServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String wordToCount = in.readLine();
            int count = 0;

            try (Socket dataSocket = new Socket("localhost", 8888);
                 BufferedReader dataIn = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()))) {

                String line;
                while ((line = dataIn.readLine()) != null && !line.equals("end_of_files")) {
                    String[] parts = line.split(" ");
                    for (String s : parts) {
                        if (s.equalsIgnoreCase(wordToCount)) {
                            count++;
                        }
                    }
                }
            }

            out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
