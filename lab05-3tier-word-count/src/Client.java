package com.mycompany.dslab5;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 7777);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter word to count: ");
            String word = sc.nextLine();
            out.println(word);

            String result = in.readLine();
            System.out.println("Total occurrences found: " + result);
        }
    }
}
