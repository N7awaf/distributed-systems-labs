/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab7;

/**
 *
 * @author nawaf
 */
import java.rmi.registry.*;

public class TimeServer {

    public static void main(String[] args) {
        try {
            TimeImp timeObj = new TimeImp();
            Registry reg = LocateRegistry.createRegistry(2300);
            reg.rebind("timeService", timeObj);
            System.out.println("Time service is available at port#2300\n");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
