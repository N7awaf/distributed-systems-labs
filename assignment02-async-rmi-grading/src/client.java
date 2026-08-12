/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsassignment_2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class client
{
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 3100);

            srvinterface service =
                    (srvinterface) registry.lookup("accService");

            Scanner input = new Scanner(System.in);

            System.out.print("enter mark: ");

            double mark = input.nextDouble();

           CompletableFuture.supplyAsync(() -> {
                try {
               
                    return service.getGrade(mark);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).thenAccept(grade -> {
            
                System.out.println("Async Callback received response from server");
                System.out.println("Async Callback the grade for mark " + mark + " is: " + grade);
            }).exceptionally(ex -> {
                System.err.println("Async Error failed to fetch grade: " + ex.getMessage());
                return null;
            });

    
            System.out.println("Main Thread request sent main thread is free to do other work simultaneously");
            for (int i = 1; i <= 3; i++) {
                System.out.println("Main Thread performing concurrent task step " + i);
                Thread.sleep(600); 
            }
            
            System.out.println("Main Thread main thread execution completed");
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
