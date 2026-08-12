package com.mycompany.dslab5;



import java.io.*;
import java.net.*;

public class DataServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                out.println("apple banana orange apple");
                out.println("apple strawberry apple");
                out.println("Riyadh Jeddah Dammam Riyadh Mecca Medina Riyadh");
                out.println("CS4841 IT-201 CS4841 CS4841 IS-300 CS4841");
                out.println("Success Excellence Success Hard-work Success Achievement Success");
                out.println("Java Python Java C++ Java JavaScript Java");
                out.println("Network Security Network Cloud Network Database Network");
                out.println("end_of_files");
            }
        }
    }
}
