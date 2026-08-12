/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsassignment_2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server
{
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.createRegistry(3100);

            srvimplementation service = new srvimplementation();

            registry.rebind("accService", service);

            System.out.println("Academic server is running on port 3100");
            System.out.println("Service 'accService' registered successfully and waiting for clients");
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
