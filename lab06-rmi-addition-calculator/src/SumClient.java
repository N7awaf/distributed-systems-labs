/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab6;

/**
 *
 * @author nawaf
 */
import java.rmi.*;
import java.util.Scanner;

public class SumClient {

    public static void main(String[] args) {
        try {
            int serverPort = 1900;
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter two numbers: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
//Obtain a reference to the object from the registry and typecast it into the appropriate type
            SumInterface s = (SumInterface) Naming.
                    lookup("rmi://127.0.0.1:"
                            + serverPort + "/myapp");
//Use the above reference to invoke the remote object's method…
            System.out.println("Output: " + s.sum(a, b));
        } catch (ConnectException conEx) {
            System.out.println("Unable to connect to server!");
            System.exit(1);
        } catch (Exception ex) {
            System.exit(1);
        }
    }
}
