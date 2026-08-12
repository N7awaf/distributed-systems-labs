/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab7;

/**
 *
 * @author nawaf
 */
import java.rmi.Naming;

public class AsyncClient {

    public static void main(String[] args) {
        try {
            SumInterface sum = (SumInterface) Naming.lookup("rmi://127.0.0.1:1900/myapp");
            TimeInterface timeService = (TimeInterface) Naming.lookup("rmi://127.0.0.1:2300/timeService");

            new Thread(() -> {
                try {
                    long remoteTime = timeService.getTime();
                    System.out.println("\n[Async] Remote time: " + remoteTime);
                    System.out.println("[Async] Formatted: " + timeService.getTimeFormatted(remoteTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            System.out.println("Main thread continues...");
            System.out.println("Sum Output (Immediate): " + sum.sum(10, 23));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
