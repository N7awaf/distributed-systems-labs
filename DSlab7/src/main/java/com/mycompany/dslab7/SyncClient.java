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
import java.io.IOException;

public class SyncClient {

    public static void main(String[] args) {
        try {
            int sumServerPort = 1900;
            int timeServerPort = 2300;

            SumInterface sum = (SumInterface) Naming.lookup("rmi://127.0.0.1:" + sumServerPort + "/myapp");
            TimeInterface timeService = (TimeInterface) Naming.lookup("rmi://127.0.0.1:" + timeServerPort + "/timeService");

            long localTime = System.currentTimeMillis();

            long remoteTime = timeService.getTime();
            System.out.println("Remote time: " + remoteTime);
            System.out.println("Remote time formatted: " + timeService.getTimeFormatted(remoteTime));

            System.out.println("Local time formatted: " + timeService.getTimeFormatted(localTime));
            System.out.println("Sum Output: " + sum.sum(10, 23));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
